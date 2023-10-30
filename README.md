# Classic Asteroid Game :rocket:

Dive into the depths of space and face an endless wave of asteroids! Built with Java and JavaFX, this rendition of the classic asteroid game offers immersive gameplay accentuated with realistic sound effects, varying levels of difficulty, and an ever-elusive alien ship.

![Game Main Screen](https://private-user-images.githubusercontent.com/123382891/279179361-4dafcdca-dc4e-4153-b86d-8ead0023741e.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE2OTg2OTg4MzMsIm5iZiI6MTY5ODY5ODUzMywicGF0aCI6Ii8xMjMzODI4OTEvMjc5MTc5MzYxLTRkYWZjZGNhLWRjNGUtNDE1My1iODZkLThlYWQwMDIzNzQxZS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMDMwJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTAzMFQyMDQyMTNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xZDNmYThjZWEwNWI1MmQxNjA1ZDE2NGJhYzU4YjM3NTg3YTUwZWM1MTE3ZmQ3ZWY3OTFlMjcwNTA5MGMwZmNlJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.ww4-yKxShs5WsqT88mjlNKV4zm23Dsr-qH7TGkSAVxw)

## :file_folder: Code Structure 

Here's a brief overview of the game's codebase structure:

- **Main.java**: This is the entry point for the game. It initializes the game window and the main menu.
  ![Main.java Screenshot](https://private-user-images.githubusercontent.com/123382891/279179312-91214149-981e-4879-86b0-54f36892deaf.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE2OTg2OTg4MzMsIm5iZiI6MTY5ODY5ODUzMywicGF0aCI6Ii8xMjMzODI4OTEvMjc5MTc5MzEyLTkxMjE0MTQ5LTk4MWUtNDg3OS04NmIwLTU0ZjM2ODkyZGVhZi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMDMwJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTAzMFQyMDQyMTNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xMTQ1ODJkODg0MjRhMDJmODJjZTQ5NDdlMWNlMmZlMzVmZDA5NmY1MzM3MGU1NDg3NDc1MTI2ZDNjZDM5MzQ4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.wAbdEhYpwyqFQZzf23NMrIjGnlRWlNY5hX2L37yya3s)
  ![Main.java Screenshot](https://private-user-images.githubusercontent.com/123382891/279179391-3f622b47-cff0-4a92-a33a-972ed2762f79.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE2OTg2OTg4MzMsIm5iZiI6MTY5ODY5ODUzMywicGF0aCI6Ii8xMjMzODI4OTEvMjc5MTc5MzkxLTNmNjIyYjQ3LWNmZjAtNGE5Mi1hMzNhLTk3MmVkMjc2MmY3OS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMxMDMwJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMTAzMFQyMDQyMTNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0wN2NhZTVlYWY3YmVmZDhlYjkwNmVkZGNkODJhMjY3MGU2MGFhZTI4OGQwYzViNTc5MTQ3ZWNiYzc4M2YwMDA0JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.GUrdqoj0diO6P4CrY9b24jk9dcq-QgCMbmyaFOu_rU4)

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


