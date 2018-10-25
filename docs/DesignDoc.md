---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics._

## Team Information
* Team name: td
* Team members
  * Ryan Vasquez
  * Brett Patterson
  * Samuel Adams
  * Henry Larson

## Executive Summary

_The application allows players to play checkers with other players who are currently signed-in. The game user 
interface (UI) supports a game experience using drag-and-drop browser capabilities for making moves. Beyond this 
minimal set of features, we have grand vision for how we could further enhance the player experience with some 
additional features beyond the basic checkers game._


### Purpose
The Web Checkers webapp was created to enhance our knowledge and implementation of object oriented design principles,
team-based software development skills, and provide a functional game of online checkers.

### Glossary and Acronyms
> _Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| VO | Value Object |


## Requirements

* Player must be able to sign in.
* Player must be able to play against another player who is also signed in.
* American rules of checkers must be implemented into the game.
* Either player must be able to resign, at any point, which ends the game.


### Definition of MVP
The Minimal Viable Product should allow the user to sign in, play a game of checkers through the American rules, 
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
* Player sign-in
* Start a game
* Checker piece movement
* Resignation
* Spectator
* Tournament play


## Application Domain

This section describes the application domain.

![The WebCheckers Domain Model](/docs/Domain-Model.png)

_There are two main entities: the game and the player(s). The game creates the board and hosts the game for two players.
The players then play the game and movement information is sent to the game, validated, and executed. A user can become
a player by signing into the application to initiate games with other players._


## Architecture and Design

The Web Checkers webapp uses a Java-based web server and was built using the Spark web micro framework and the 
FreeMarker template engine to handle HTTP requests and generate HTML responses.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

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

![The WebCheckers Web Interface Statechart](web-interface-placeholder.png)

> _Provide a summary of the application's user interface.  Describe, from
> the user's perspective, the flow of the pages in the web application._


### UI Tier
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
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._


### Model Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

### Design Improvements
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
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._
