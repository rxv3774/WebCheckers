---
geometry: margin=1in
---
# Web Checkers Design Documentation

## Team Information
* Team name: td
* Team members
  * Ryan Vasquez
  * Brett Patterson
  * Samuel Adams
  * Henry Larson

## Executive Summary

This is a web-based application where players, who are signed in, have the ability to play checkers with other players.
 The user interface for the game will implement drag-and-drop functionality for making moves. Furthermore, the additional
 features of "Spectator Mode" and "Tournament Play" will be implemented to enhance the user experience.


### Purpose
Web Checkers exists to allow users of all skill levels to play a web-based version of checkers. This enables
individuals to play with others in different locations, instead of limiting them to a face to face board game.
Whether someone is interested in playing against a friend or even just a player in the lobby they've never met 
before, Web Checkers offers a fun and challenging way to pass time. 

### Glossary and Acronyms

| Term | Definition |
|------|------------|
| VO | Value Object 
| Web-based application | A web-based application is a program that is accessed over a network connection using HTTP, rather than existing within a device's memory.
| Pure Fabrication pattern | A Pure Fabrication is a set of responsibilities held within a separate class that does not represent an entity in the model.

## Requirements

* Player must be able to sign in.
* Player must be able to play against another player who is also signed in.
* American rules of checkers must be implemented into the game.
* Either player must be able to resign, at any point, which ends the game.


### Definition of MVP
The Minimal Viable Product should allow the user to sign in to a web-based system, play a game of checkers through the American rules, 
and resign at any point which ends the game.

### MVP Features

###### Epics: 
* Checker movement
* Resignation

###### User Stories: 
* Move first
* Move on turn
* Single move
* Multi direction
* Single jump move
* Multi jump move
* Resignation


### Roadmap of Enhancements
* Spectator: User can enter a game already in progress and watch, but not play.

* Tournament play: User can choose to play in tournament mode, where a group of players compete in a system of games that ends with one winner. 


## Application Domain

This section describes the application domain.

:![The WebCheckers Domain Model](Domain-Model.png)

_There are two main entities: the game and the player(s). The game creates the board and hosts the game for two players.
The players then play the game and movement information is sent to the game, validated, and executed. A user can become
a player by signing into the application to initiate games with other players._


## Architecture and Design

The Web Checkers webapp uses a Java-based web server and was built using the Spark web micro framework and the 
FreeMarker template engine to handle HTTP requests and generate HTML responses.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

:![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

As a web application, the user interacts with the system using a
browser.  The client-side of the UI is composed of HTML pages with
some minimal CSS for styling the page.  There is also some JavaScript
that has been provided to the team by the architect.

The server-side tiers include the UI Tier that is composed of UI Controllers and Views.
Controllers are built using the Spark framework and View are built using the FreeMarker framework.  The Application and Model tiers are built using plain-old Java objects (POJOs).

Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the WebCheckers application.

:![The WebCheckers Web Interface Statechart](Statechart.png)


From the Perspective of the user, the Application's user interface begins on the home page
where the user will see a welcome screen and a button to sign in. When the user clicks the button,
the interface then flows to the sign-in page, where the user is prompted to enter a unique name.
If the user enters an invalid name or name that's already being used, the user will stay on the sign-in
page but with the appropriate error message. If the user enters a valid name, the interface flows back to the home page, where the
user can see his name as the current player, along with a number or list of other players in the lobby. When the 
user chooses a player to enter a game with or a different player chooses the user to play a game with, 
the user is transitioned to the game page, where the board is laid out in game form. If a player wins or 
if either player resigns, both are taken back to the home page.  


### UI Tier
The UI Tier of Web Checkers begins with WebServer, which is responsible for initializing all of the HTTP Routes that make up the web application.
When a client navigates to the Web Checkers page, he will be starting in the GetHomeRoute component of the UI Tier.
If client attempts to start at a page that is not the home page, the client will be redirected to the sign in page. 
GetHomeRoute is responsible for displaying the home page, with a Sign In button at the top and additional information in the body. When the Sign In button is clicked, the client will be sent
to GetSignInRoute, which is responsible for displaying the sign in page. On the sign in page, the client can type a name into the space provided and press the submit button, 
which will send the client to PostSignInRoute. In PostSignInRoute, if the username is invalid or already taken, the user will remain on the sign in page but with an error message; 
if the username is valid and unique, the player will be signed in and redirected back to the home page.
This process of signing in, from the perspective of the User Interface, can be seen in the following sequence diagram:

:![Sign In Sequence Diagram](Sign%20In%20Sequence%20Diagram.png)


Back in GetHomeRoute, the current lobby will now be displayed and, if there is more than one player in the lobby, there will be
an option to select a player and start a game. Once the client chooses an opponent and clicks the start game button, the client is sent to GetGameRoute.
GetGameRoute is responsible for creating a match in GameCenter (if one does not already exist) and displaying the game page
to the client. The second player is added to the game page after the match is created in GameCenter, since the opponent is still in GetHomeRoute; this
works by GetHomeRoute consistently checking if the player is contained in GameCenter, and if it is then it will 
redirect the player to the game page.


> _Provide a summary of the Server-side UI tier of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class structure or object diagrams) with some
> details such as critical attributes and methods._

> _You must also provide any dynamic models, such as statechart and
> sequence diagrams, as is relevant to a particular aspect of the design
> that you are describing.  For example, in WebCheckers you might create
> a sequence diagram of the `POST /validateMove` HTTP request processing
> or you might show a statechart diagram if the Game component uses a
> state machine to manage the game._

> _If a dynamic model, such as a statechart describes a feature that is
> not mostly in this tier and cuts across multiple tiers, you can
> consider placing the narrative description of that feature in a
> separate section for describing significant features. Place this after
> you describe the design of the three tiers._


### Application Tier
The application tier contains two components: GameCenter and PlayerLobby. When a player signs in, 


### Model Tier
> _Provide a summary of the Model tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

### Significant Features

_(Figure 176)_
:![Start A Game Sequence Diagram](Start%20A%20Game%20Sequence%20Diagram.png)

### Design Improvements

If given the opportunity to
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements. After completion of the Code metrics exercise, you
> will also discuss the resutling metric measurements.  Indicate the
> hot spots the metrics identified in your code base, and your
> suggested design improvements to address those hot spots._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
As of now, we have completed and passed all necessary acceptance criteria tests for 
the first two user stories. All acceptance criteria tests for the Player sign-in and start 
game user stories are complete and functional. We are currently working on tests for further 
user stories.


### Unit Testing and Code Coverage
For our unit tests, our goal was to reach at least 90% coverage on all tiers(model, application, and UI). 
We decided on 90% because, it would give us confidence that we are properly implementing the different tiers. 
As we created tests, we began to realize that we had certain coupling issues which we were able to quickly fix. 
This fix was necessary, as these issues were preventing us from creating a proper testing seam between our classes. 
This was due to that fact that we had not followed the Pure Fabrication pattern. These issues helped us improve our 
code, as we realized that hard to test code is code that could use some improvements.