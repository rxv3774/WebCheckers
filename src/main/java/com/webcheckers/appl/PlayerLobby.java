package com.webcheckers.appl;

import com.webcheckers.ui.WebServer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.halt;


/*
 * Signs in and stores all users and their sessions.
 */
public class PlayerLobby {
    /**
     * Array of all the players in the current lobby.
     */
    private ArrayList<Player> players = new ArrayList<>();
    private Map<String, Session> sessionMap = new HashMap<>();


    private static final String MESSAGE_ATTR = "message";
    private static final String MESSAGE_TYPE_ATTR = "messageType";
    private static final String VIEW_NAME = "signin.ftl";
    private static final String HOME_NAME = "home.ftl";


    /**
     * Attempts to add a player to the array of players. Only works if new username is not already taken.
     * @param player The new player that is being added.
     */
    public void addPlayer(Player player) {
        if( isValidName( player.getName() ) ) {
            players.add(player);
//            System.out.println( player.getName() + " has been added" ); // Print to website, not console
        }
    }


    /**
     * Desc: This method determines if the playerName fallows our naming convention.
     * @param name the name that needs to be checked
     * @return true if it fallows our rules, false otherwise
     */
    private boolean isValidName(String name) {

        if( name.equals( "" ) ){
            return false;
        }

        String allowed = "abcdefghijklmnopqrtsuvwyxzABCDEFGHIJKLMNOPQRTSUVWYXZ0123456789 ";

        for( int i = 0; i < name.length(); i++){
            if( !allowed.contains( name.substring(i, i+1) )){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the name name that is being created is already in use by another player.
     * @param name The name of the player that is trying to be created.
     * @return Whether or not the user is taken.
     */
    public boolean playerNameInUse(String name){

        for( Player player : players) {
            if( player.getName().equals( name )) {
                return true;
            }
        }
        return false;
    }


    /**
     * Prints out the list of all the players in the lobby.
     * @return List of players.
     */
    public ArrayList<Player> printPlayers() { //TODO: fix so that it prints to the to the website, not the console.
        return players;
    }


    public ArrayList<String> getPlayersNames() {
        ArrayList<String> names = new ArrayList<>();

        for( Player player : players){
            names.add( player.getName() );
        }

        return names;
    }

    /*
     * return player object given name of player
     */
    public Player getPlayerObject(String name){
        for (int i=0; i<players.size(); i++){
            if(players.get(i).getName().equals(name))
                return players.get(i);
        }
        return null;
    }


    /**
     * Desc: Gets the number of players in the lobby.
     * @return Size of lobby.
     */
    public int getLobbySize() {
        return players.size();
    }

    /*
     *Signin the user if their chosen username is valid. Store the players list and current player
     * name in the session.
     *
     * @param name: The chosen name of the player who wants to sign in
     * @param request:
     */
    public ModelAndView playerSignInProcess(String name, Request request, Response response, Map<String, Object > vm){

        ModelAndView mv;

        if( !isValidName( name ) ){ // invalid name (blank)
            mv = invalidPlayerName( vm );
            return mv;
        }

        if( playerNameInUse( name ) ){

            return playerNameUsedAlready( vm );
        }
        if( !playerNameInUse( name ) ){ // No one is using this name.

            Player newPlayer = new Player( name );

            if( !players.contains( newPlayer ) ) {
                Session httpSession = request.session();
                httpSession.attribute("playerNames", players);
                httpSession.attribute("name", name);
                sessionMap.put( name, httpSession );
            }

            addPlayer( newPlayer );

            response.redirect( WebServer.HOME_URL );
            halt();
            return null;

        }

        System.out.println("reached this.... This is bad");
        return null;
    }

    /**
     * Gets in game player names.
     * @return copied TreeSet of players names if those players are in game.
     */
    public List<String> getInGamePlayerNames() {
        ArrayList<String> playersOut  = new ArrayList<>();
        for(Player p : this.players) {
            if (p.isInGame())
                playersOut.add(p.getName());
        }
        return playersOut;
    }

    /**
     * Desc: gets the players session
     * @param playerName the players name to be used to get the session
     * @return returns the players session
     */
    public Session getPlayerSession(String playerName) {
        return sessionMap.get(playerName);
    }

    public String getPlayerNameLst(String name){


        ArrayList<String> playerNameLst = new ArrayList<>( getPlayersNames() );
        playerNameLst.remove( name );

        String names = "";
        if( playerNameLst.size() > 0) {

            names += "Other Players signed in: ";

            for (int x = 0; x < playerNameLst.size(); x++) {

                if (x < playerNameLst.size() - 1) {
                    names += playerNameLst.get(x) + ", ";
                } else {
                    names += playerNameLst.get(x);
                }
            }
        }
        else {
            names = "Number of players signed in: " + getLobbySize();
        }
        return names;
    }


    /*
     * Error message for when requested user name contains illegal characters
     *
     * @param vm: virtual map for the modelandview
     * @return ModelAndView: updated modelandview with error message
     */
    private ModelAndView invalidPlayerName(Map<String, Object> vm){
        vm.put( "title", "Sign-In" );
        vm.put("messageType", "error" );
        vm.put( "showErrorMessage", "you entered illegal characters in the name. Please enter a different name");
        return new ModelAndView(vm, VIEW_NAME);
    }

    /*
     * Error message for when requested user name is already in use
     *
     * @param vm: virtual map for the modelandview
     * @return ModelAndView: updated modelandview with error message
     */
    private ModelAndView playerNameUsedAlready(Map<String, Object> vm){
        vm.put( "title", "Sign-In" );
        vm.put("messageType", "error" );
        vm.put( "showErrorMessage", "you entered an already used name. Please enter a different name");
        return new ModelAndView(vm, VIEW_NAME);
    }

}
