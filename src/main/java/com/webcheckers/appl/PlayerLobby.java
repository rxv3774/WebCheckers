package com.webcheckers.appl;

import com.webcheckers.model.Player;
import spark.ModelAndView;
import spark.Request;
import spark.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

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
    private void addPlayer(Player player) {
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

        String allowed = "abcdefghijklmnopqrtsuvwyxzABCDEFGHIJKLMNOPQRTSUVWYXZ0123456789";

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
    public boolean playerNameInUse( String name){

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


    /**
     * Desc: Gets the number of players in the lobby.
     * @return Size of lobby.
     */
    public int getLobbySize() {
        return players.size();
    }


    public ModelAndView playerSignInProcess(String name, Request request, Map<String, Object > vm){

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
            addPlayer( newPlayer );
//            players.add( newPlayer );

            // retrieve the HTTP session
            final Session httpSession = request.session( true );

            // create the user playerName attribute and put into the playerName into the session
            httpSession.attribute("playerName", name);
            sessionMap.put( name, httpSession);

            return newPlayerAdded( vm, name);
        }

        System.out.println("reached this.... This is bad");
        return null;
    }


    private ModelAndView invalidPlayerName(Map<String, Object> vm){
        vm.put( "title", "Sign-In" );
        return new ModelAndView(vm, VIEW_NAME);
    }


    private ModelAndView playerNameUsedAlready(Map<String, Object> vm){
        vm.put( "title", "Sign-In" );
        return new ModelAndView(vm, VIEW_NAME);
    }

    private ModelAndView newPlayerAdded(Map<String, Object> vm, String name){
        vm.put( "title", name );
        return new ModelAndView(vm, HOME_NAME);
    }

}
