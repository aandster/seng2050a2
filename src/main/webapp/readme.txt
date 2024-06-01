Student Name: Jordan Maddock
Student Number: 3259753
Course: SENG2050
Date: 15/04/2024

 # ASSIGNMENT 2

 ## HOW TO RUN

    1. Go to .../c3259753_assignment2/start

    2. On /login page, Enter your name

    3. On /play page, guess your letters

    4. Eventually, when you solve it, you will see a "Finished" message with score

    5. Click New Game or Logout



 ## DESIGN AND IMPLEMENTATION NOTES

 ### How is booking data stored?

    Booking data is stored in a text file at /booking_records.txt.

    Format is similar to CSV, with fields delimited with | symbols, and records delimited by new lines.

    The class BookingFileConnector class handles file IO operations.


 ### What is the application structure, i.e. relationships between objects

    - The Persistence package has objects that read the text files in the data folder of webapps.

    - The Servlets package has our servlets:
        - StartServlet is for the login screen.
        - PlayServlet is for seeing/playing your game, and see your finished results.
        - GuessServlet handles form submissions when guessing letters.
        - LogoutServlet clears the username from the session and redirects to StartServlet.

    - The Model package has a Bean and a Model object
        - GameBean is the bean. It's just getters and setters.
        - GtwGame is where pretty much all of the game logic is implemented.