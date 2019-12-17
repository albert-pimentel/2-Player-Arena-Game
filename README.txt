=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: 12253292
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays - Used in implementing the 2D board on which players 1 and 2 duel. 
  This feature is appropriate because it allows for easy traversal across tiles and 
  allows for an easy method of detecting whether or not players are "colliding"  
  (on the same tile) or attempting to move outside of the board. My received 
  feedback largely agreed that this is an appropriate data structure.

  2. JUnit - Used in testing the dueling / image updating methods. 
  JUnit is used to check that the appropriate player subclass can attack 
  players when intended, and that when players do attack, the attacked 
  player's values change correspondingly. JUnit is also used to check that 
  inputting an invalid text file does not crash the program and that inputting 
  valid text files change the image. My received feedback informed me to 
  ensure that my testing depends on the model, not user input, which is 
  what I implemented.

  3. Inheritance / subtyping for dynamic dispatch - Used in creating 
  subtypes of the player class. The superclass, Player, provides getters 
  and setters as well as some abstract methods. The subclasses, 
  Knight, Ranger, and Bomber all provide different implementations of 
  the attack methods and have varying health / attack fields. 
  The Knight class has high health and high attack, but can only 
  attack players in the tile directly in front of it. The Ranger class 
  has medium health and low attack, but can attack any player within
  a straight line in the direction it is facing. The Bomber class 
  has medium health and low attack, but can attack any player within the 
  3x3 tile area centered on its current tile (truncates if any said tile is 
  outside of the board). 

  4. File I/O - Used in reading and writing into a file that lists 
  the number of wins for every Player subclass (Knight, Ranger, 
  Bomber), regardless of which player plays which class. File I/O 
  is the best way to do this because having a permanent file from which 
  to read and write allows for seeing the number of wins across classes, 
  even when the game window is closed. These scores are sorted in descending
  order, as my received feedback reminded me to do so.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Player - Superclass of Knight, Ranger, Bomber. Central game object.
           Contains basic getters, setters, fields, as well as 
           abstract methods that subclasses inherit.
  Knight - Subclass of Player. Contains specifics to the knight 
           arena class, such as high health and attack fields. 
           Implements draw method and unique attack method.
  Ranger - Subclass of Player. Contains specifics to the ranger
           arena class, such as medium health and attack fields. 
           Implements draw method and unique attack method.
  Bomber - Subclass of Player. Contains specifics to the bomber 
           arena class, such as medium health and attack fields. 
           Implements draw method and unique attack method.
  TileBoard - Allows for 2 players to exist and be related to 
           a tileboard (2D Array). Allows for the manipulation 
           of players within the context of their respective 
           tileboard (such as ensuring that players do not 
           move outside of the board).   
  ScoreObtainer - Class that allows for the reading and writing 
                  of high scores in the "highscores.txt" file.
                  Also sorts highest scores.
  GameController - Analogous to GameCourt in the Mushroom of Doom 
                   demo. Sets up a timer, window width and height, 
                   keylisteners, random class selection, and more.
  Game - Main class which must be run in order to begin a new 
         game. 
  GameTest - File for testing the 2D arena model.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Figuring out how to use Swing was the most time-consuming part of this 
  project, but once I learned how to use it, finishing was manageable.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I feel that my design was efficient. Player and its subclasses are 
  separate from TileBoard, to allow for TileBoard to independently 
  manipulate the Player class. ScoreObtainer is separated from both 
  Player and its subclasses, but is used by TileBoard. Finally, GameController 
  manipulates all classes except Game, and Game manipulates GameController. 
  Thus, there exists separation of functionality. Each class has 
  private, encapsulated fields and methods, as well as getters and setters. 
  I do not think I would refactor anything.

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  