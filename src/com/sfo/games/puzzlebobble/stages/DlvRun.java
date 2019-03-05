package com.sfo.games.puzzlebobble.stages;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.sfo.games.puzzlebobble.entities.Sphere;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class DlvRun {

	private final String service = "libs/dlv.mingw.exe";
	Handler handler;
	private List<Vector2> posRaggiungibili;
	private static String encodingResource= "encoding/puzzleBobble";
	InputProgram facts;
	InputProgram encoding;
	private int positionGoodX;
	private int positionGoodY;
	private float pixelY = -120;
	private float pixelX;
	private static DlvRun instance = null;

	public float getPixelY() {
		return pixelY;
	}


	public void setPixelY(int y) {
		this.pixelY = (float) ((y * Sphere.Size) + StagePlay.TOP_BORDER + (Sphere.Size / 2)) + Sphere.Size / 2.0F - 45.0F;
	}


	public float getPixelX() {
		return pixelX;
	}


	public void setPixelX(int x) {
		if(pixelY != -120)
			this.pixelX = (float) ((x * Sphere.Size) + StagePlay.LEFT_BORDER + pixelY % 2 * (Sphere.Size / 2)) + Sphere.Size / 2.0F ;
	}


	public static DlvRun getInstance() {
		if(instance == null)
			instance = new DlvRun();
		return instance;
	}


	public int getPositionGoodX() {
		return positionGoodX;
	}

	public void setPositionGoodX(int positionGoodX) {
		this.positionGoodX = positionGoodX;
	}

	public int getPositiongoodY() {
		return positionGoodY;
	}

	public void setPositiongoodY(int positiongoodY) {
		this.positionGoodY = positiongoodY;
	}

	public DlvRun() {
		handler = new DesktopHandler(new DLVDesktopService(service));
		facts = new ASPInputProgram();
		encoding = new ASPInputProgram();
		this.encodeProgram(encodingResource);
	}

	void encodeProgram(String encode) {
		encoding.addFilesPath(encode);
		handler.addProgram(encoding);
	}

	public void run(){

		this.addFact();
		Output o = handler.startSync();
		AnswerSets answ = (AnswerSets) o;
		//System.out.println(answ.getAnswersets().size());
		System.out.println(answ.getAnswerSetsString());

		for(AnswerSet a: answ.getAnswersets()) {

			for(int i = 0; i< a.getAnswerSet().size(); i++) {
				String atom = a.getAnswerSet().get(i).toString();
				//				if(atom.substring(0, 6).equals("adj2SP")) {
				//					System.out.println(atom);
				//				}

				if(atom.substring(0, 12).equals("positionGood")) {
					System.out.println(atom);
					positionGoodX = Integer.parseInt(atom.substring(15,16));
					positionGoodY = Integer.parseInt(atom.substring(13, 14)) * -1;
					this.setPixelY(positionGoodY);
					this.setPixelX(positionGoodX);
				}



			}

		}
	}

	public void addFact() {
		facts.clearAll();
		posRaggiungibili = this.computeReached(StagePlay.getInstance().getSpheres());

		for(Vector2 element : posRaggiungibili)
			try {
				facts.addObjectInput(new Position(element));

			} catch (Exception e) {
				e.printStackTrace();
			}

		for(Sphere s : StagePlay.getInstance().getSpheres())
			try {
				facts.addObjectInput(new PositionColorSphere(s));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Sphere current = StagePlay.getInstance().getCurrentSphere();
		try {
			facts.addObjectInput(new ColorCurrentSphere(current));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sphere next = StagePlay.getInstance().getNextSphere();
		try {
			facts.addObjectInput(new ColorNextSphere(next));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		handler.addProgram(facts);
		System.out.println(handler.getInputProgram(0).getPrograms());
	}




	public  List<Vector2> computeReached (List<Sphere> present){
		List<Vector2> freeAllPosition = new ArrayList<>();
		List<Vector2> allPosition =  new ArrayList<>();
		List<Vector2> posRaggiungibili = new ArrayList<>();
		
		int [][] matrix = new int[10][8];

		for(int i = 0; i < 9; i++) {	
			for(int j = 0; j < 8; j++) {
				matrix[i][j] = 3;
			}
		}
		//ultima riga starndard 
		for(int i = 0; i < 8; i++) 	
			if(i == 4)
				matrix[9][i] = 2; //destination
			else 
				matrix[9][i] = 0;  //wall



		//aggiungo le sfere
		for(Sphere sphere : present)
			matrix[(int) sphere.YsuMatrice()][(int) sphere.XsuMatrice()] = 0;



		for(int i = 0; i <= 7; i++) {	
			for(int j = 0; j <= 8; j++) {
				boolean p = false;
				if( (j & 1) != 0 && i == 0 ) 
					p = true;

				if(!p) {
					allPosition.add(new Vector2(j,i));
				}


			}	
		}

		for(Vector2 position : allPosition) {
			boolean presente = false;

			for(Sphere sphere : present)
				if(sphere.XsuMatrice() == position.y && sphere.YsuMatrice() == position.x) {
					presente = true;
				}

			if(!presente) {
				boolean haVicino = false;

				for(Sphere sphere : present)
					if(StagePlay.getInstance().nextTo(sphere, position)) {

						haVicino = true;
					}

				//controlla le posizioni che non sono raggiungibili anche avendo un vicino e quelle che possono attaccarsi al bordo superiore****
				if(haVicino || position.x == 0.0) {

					freeAllPosition.add(position);
				}


			}
		}

		System.out.println("Size ALLFREEPOSITION: " + freeAllPosition.size());
		System.out.println("Size ALLPOSITION: "+allPosition.size());

		
		Path cammino = new Path();
		
		for(Vector2 position : freeAllPosition) {
			matrix[(int)position.x][(int)position.y] = 1;
			
			
			System.out.println("MATRICE---------------------------");
			System.out.println();
			
			for(int i = 0; i < 10; i++) {	
				for(int j = 0; j < 8; j++) {
					//System.out.print(matrix[i][j]+", ");
				}
				//System.out.println();
			}

			System.out.println("Controllo la posizione: "+position.toString());
			if(cammino.isPath(matrix, 10, 8)){
				posRaggiungibili.add(position);
				System.out.println("posizione inserita: "+position.toString());
			}	

			matrix[(int)position.x][(int)position.y] = 3;
			

		}
			
		
		System.out.println("Size POS RAGGIUNGIBILI: "+posRaggiungibili.size());

		for(Vector2 position : posRaggiungibili)
			System.out.println("posRaggiungibili: "+position.toString());
			
		return posRaggiungibili;
	}


}
