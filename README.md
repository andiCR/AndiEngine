AndiEngine
==========
ABOUT:
AndiEngine is a basic Java game engine created with the purpose of teaching game programming basics to students on the
the Android platform. I am very used to the Cocos2D platform, but the Cocos2D-android project has little to no support.
Hence the new engine.
This engine was created by Andres "Andi" Cartin.

HOW IT WORKS:
AndiEngine uses a class called "AndiEngineView" to create its canvas by inheriting from Android's SurfaceView (not the
GLSurfaceView). Just use this on your activity and set it as contentView.


  	// Create scene and add to engine
		AndiEngineView engineView = new AndiEngineView(this);
		scene = new MyFirstScene(this);
		engineView.setScene(scene);

		// Set content view
		setContentView(engineView);//R.layout.activity_main);


The graphics engine is based on a scene graph. With a base Scene node that distributes drawing and updating to all
its underlying children nodes. Scene graphs are useful because you can group similar objects and transform them
together. This is an example of how I would normally lay out my objects on the scene:

SCENE
==> GAME LAYER
======> BACKGROUND
======> CHARACTER
===========>CHARACTER NAME
==> UI LAYER
======> SCORE
======> MENU BUTTON

The cool thing is that if I move the character node in the example above, the character name moves with it. At the
same time, if I wanted to shake the screen, I would move the game layer node which would move the background and the
character. The UI layer wouldnt move as it is another child of the scene.

I have included basic nodes that can let you get started:
Node -> Base node for all operations. Transformations such as position, rotation and scale are included in this node.
This node also contains children nodes.
Scene -> Base node for a scene. Inherit from this to create your own scene.
TextNode -> Draws a text on the scene
SpriteNode -> Basic sprite node. Create a sprite with an image from your project pretty easily.
AnimatedSpriteNode -> Creates a sprite based on a sprite sheet. There are several steps to initialize this:
1. Specify the size of each of the images in the sprite sheet.
2. Add animations with the coordinates (row/column) of the animation
3. Set an animation
This is an example of how to use the AnimatedSpriteNode:

  // Add main character
  mainCharacter = new AnimatedSpriteNode(R.drawable.yoshi, c, 80, 80);
	mainCharacter.addAnimation("down",  new Point[] {new Point(0,0),new Point(0,1),new Point(0,2),new Point(0,3),new Point(0,4)});
	mainCharacter.addAnimation("right", new Point[] {new Point(1,0),new Point(1,1),new Point(1,2),new Point(1,3),new Point(1,4)});
	mainCharacter.addAnimation("up",  	new Point[] {new Point(2,0),new Point(2,1),new Point(2,2),new Point(2,3),new Point(2,4)});
	mainCharacter.addAnimation("left",  new Point[] {new Point(3,0),new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4)});
	mainCharacter.setAnimation("down");
	mainCharacter.setAnimationSpeed(0.2f);

Feel free to email any questions to skullfirec@gmail.com. If you want to contribute, don't hesitate on asking.
