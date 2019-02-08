package com.sfo.games.puzzlebobble.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sfo.games.puzzlebobble.helpers.InputHelper;
import com.sfo.games.puzzlebobble.helpers.MathHelper;
import com.sfo.games.puzzlebobble.stages.DlvRun;
import com.sfo.games.puzzlebobble.stages.StagePlay;

public class Cannon
{
	private float rotateSpeed = 0.2F;
	private float shootSpeed = 0.6F;
	private float targetAngle = 0.0F;
	private Vector2 targetVector = Vector2.Zero;
	private Sprite base;
	private Sprite rotor;
	private Sprite arrow;
	private Sound shootingSound;
	private Sound hammeringSound;

	public Cannon()
	{
		this.shootingSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/8bithurt.wav"));
		this.hammeringSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/hammerclick.wav"));

		this.base = new Sprite(new Texture(Gdx.files.internal("assets/images/cannon-base.png")));
		this.rotor = new Sprite(new Texture(Gdx.files.internal("assets/images/cannon-rotor.png")));
		this.arrow = new Sprite(new Texture(Gdx.files.internal("assets/images/cannon-arrow.png")));
		this.rotor.setOrigin(this.rotor.getWidth() / 2.0F, this.rotor.getHeight() / 2.0F);
		this.rotor.setScale(0.85F);

		this.arrow.setOrigin(this.arrow.getWidth() / 2.0F, this.arrow.getHeight() / 3.0F);
	}

	public void dispose()
	{
		this.shootingSound.dispose();
		this.hammeringSound.dispose();
	}

	public void draw(SpriteBatch spriteBatch)
	{
		this.rotor.draw(spriteBatch);
		this.arrow.draw(spriteBatch);
		this.base.draw(spriteBatch);
	}

	public void reset()
	{
		
		this.arrow.setRotation(0.0F);
		this.rotor.setRotation(0.0F);
	}

	public void setPosition(float x, float y)
	{
		
		float x2 = x - this.rotor.getWidth() / 2.0F;
		float x3 = x - this.arrow.getWidth() / 2.0F;
		float x4 = x - this.base.getWidth() / 2.0F;
		this.rotor.setPosition(x2, y);
		this.arrow.setPosition(x3, y + 20.0F);
		this.base.setPosition(x4, y);
	}

	public void rotateRight(float degrees)
	{
		float darrow = this.arrow.getRotation();
		if (darrow < -84.0F) { return; }
		float drotor = this.rotor.getRotation();

		this.rotor.setRotation(drotor - degrees * 3.0F);
		this.arrow.setRotation(darrow - degrees);
	}

	public void rotateLeft(float degrees)
	{
		
		float darrow = this.arrow.getRotation();
		if (darrow > 84.0F) { return; }
		float drotor = this.rotor.getRotation();

		this.rotor.setRotation(drotor + degrees * 3.0F);
		this.arrow.setRotation(darrow + degrees);
	}

	public void target(float x, float y)
	{
		
		this.targetVector.x = x;
		this.targetVector.y = y;

		float ax = this.arrow.getX() + this.arrow.getOriginX();
		float ay = this.arrow.getY() + this.arrow.getOriginY();
		this.targetAngle = ((float)MathHelper.angle(ax, ay, x, y));
	}

	public void update(float delta)
	{
		float currentAngle = this.arrow.getRotation();
		if (currentAngle != this.targetAngle)
		{
			if (Math.abs(this.targetAngle - currentAngle) < this.rotateSpeed * delta)
			{
				this.arrow.setRotation(this.targetAngle);
				return;
			}
			if (this.targetAngle > currentAngle)
				{ rotateLeft(this.rotateSpeed * delta); }
			else
				{ rotateRight(this.rotateSpeed * delta); }
		}
	}

	public boolean touched(float touchX, float touchY)
	{
		
		if (touchX < this.rotor.getX()) {
			return false; 
		}
		if (touchY < this.rotor.getY()) { 
			return false; 
		}
		if (touchX > this.rotor.getX() + this.rotor.getWidth()) { 
			return false;
		}
		if (touchY > this.rotor.getY() + this.rotor.getHeight()) { 
			return false; 
		}
		return true;
	}
	int cont = 0;
	public void shoot(Sphere sphere)
	{
		
		//StagePlay.getInstance().getCannon().target(DlvRun.getInstance().getPixelX(), DlvRun.getInstance().getPixelY());
		
		if (sphere.state() == Sphere.State.Ready && cont >= 100)
		{
			
			InputHelper.vibrate(200);
			InputHelper.play(this.shootingSound);
			float offsety = MathHelper.offsetY(this.arrow.getRotation(), this.shootSpeed);
			float offsetx = MathHelper.offsetX(this.arrow.getRotation(), this.shootSpeed);
			sphere.setDirection(offsety, offsetx);
			
			sphere.move();
			cont = 0;
			
		}
		else
		{
			while(cont < 100) {
					cont++;
					break;
			}
			
			
			
			
			
			
			
				
		}
	}
}

