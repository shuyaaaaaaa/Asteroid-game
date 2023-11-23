# Classic Asteroid Game :rocket:

Dive into the depths of space and face an endless wave of asteroids! Built with Java and JavaFX, this rendition of the classic asteroid game offers immersive gameplay accentuated with realistic sound effects, varying levels of difficulty, and an ever-elusive alien ship.

![Game Main Screen](https://github.com/shuyaaaaaaa/Asteroid-game/assets/123382891/771c741e-42fd-4ff2-98c9-6c0e505be2f3)

## :file_folder: Code Structure 

Here's a brief overview of the game's codebase structure:

- **Main.java**: This is the entry point for the game. It initializes the game window and the main menu.
  ![Main.java Screenshot](https://github.com/shuyaaaaaaa/Asteroid-game/assets/123382891/d53b1f34-ea58-4d51-9c29-406eadb27339)
  ![Main.java Screenshot](https://github.com/shuyaaaaaaa/Asteroid-game/assets/123382891/1d1681a3-6b06-4236-85a8-f3619998acdc)

- **Entity Classes**: Define game objects and their behaviors.
  - AlienShip.java
  - Asteroid.java
  - Bullet.java
  - PlayerShip.java
  - Dust.java

- **Utility Classes**: Responsible for game dynamics and various functionalities.
  - ComponentGenerator.java: Generates game components dynamically.
  - Constant.java: Holds constant values used across the game.
  - Generator.java: General generation utility.
  - ScoreManager.java: Manages player scores and the Hall of Fame.
  - SoundEffect.java & SoundManager.java: Handle in-game sound effects.

- **UI & Menu**:
  - NewAsteroidsMenu.java: The game's main menu.
  - AsteroidSize.java & DustProduce.java: Manage the size and generation of asteroid particles.

- **AsteroidsGame.java**: The core game logic.


## :star: Features

- **Dynamic Gameplay**: Experience increasing difficulty with more asteroids and frequent alien ship appearances as you progress through levels.
- **Realistic Sound Effects**: Every action, from shooting lasers to asteroids exploding, is paired with immersive sound effects.
- **Hall of Fame**: Keep track of top scores and compete for a spot in the Hall of Fame!
- **Sleek Graphics**: Built with JavaFX, enjoy a seamless and visually captivating gaming experience.

## :gear: Technical Stack

- **Language**: Java
- **Graphics & UI**: JavaFX

## :book: Installation & Setup

To get the game up and running on your local machine, follow these steps:

1. **Install Java**: Ensure you have Java installed. If not, download and install from [Java Official Site](https://www.java.com/en/download/).
   
2. **Install JavaFX**: 
    - Download the JavaFX SDK from [OpenJFX](https://openjfx.io/).
    - Extract the SDK to a folder on your system.
    - Add the `lib` folder from the extracted SDK to your system's `PATH`.

3. **Run the Game**:
    - Clone the repository to your local machine.
    - Navigate to the game directory.
    - Compile

## :mailbox: Feedback & Contributions

Love the game? Found a bug? Have suggestions? Feel free to contribute or provide feedback! Your input is valued and helps improve the gameplay experience for everyone.


