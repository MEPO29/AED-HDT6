import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class HT6 {

    public static void main(String[] args) {
        int mapType;
        int optionMenu = 0;
        
        Scanner read = new Scanner(System.in);
        
         Map<String, String> cards;
         Map<String, String> totalCards; 
         MapFactory factory = new MapFactory();
         //Decks de cartas
         ArrayList<String> cardName = new ArrayList<String>();
         ArrayList<String> characters = new ArrayList<String>();
         ArrayList<String> cardNamePlayer = new ArrayList<String>();
        ArrayList<String> allCards = new ArrayList<String>();
        try{
            Stream<String> lines = Files.lines(Paths.get("cards_desc.txt"),StandardCharsets.UTF_8);
            lines.forEach(a -> allCards.add(a));
        }catch(IOException e){
            System.out.println("Error al leer el archivo, asegurar que este colocado dentro del folder del proyecto");
        }
        
        //Menu
        System.out.println("Ingrese el tipo de mapa que desea utilizar: ");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        
        mapType = read.nextInt();
        
        //Programacion defensiva
        if (mapType >= 4){
            System.out.println("Esta opcion no se encuentre el menu");
            System.exit(0);
        }
        
        cards = factory.ChooseMap(mapType);
        totalCards = factory.ChooseMap(mapType);
        
        //Separar info de las cartas
        for (String i: allCards) {
	                //System.out.println(i);
	                String[] information = i.split("[|]");
	                cardName.add(information[0]);
	                totalCards.put(information[0], information[1]);
                        //System.out.println(totalCards);
	    }
        
        
        
        while(optionMenu != 7){
            //Segundo menu
            System.out.println("Seleccione la operación que desea realizar: ");
            System.out.println("1. Agregar una carta a la coleccion");
            System.out.println("2. Mostrar el tipo de una carta especifica");
            System.out.println("3. Mostrar el nombre, tipo y cantidad de cada carta en su colección.");
            System.out.println("4. Mostrar el nombre, tipo y cantidad de cada carta en su colección, ordenadas por tipo.");
            System.out.println("5. Mostrar el nombre y tipo de todas las cartas existentes.");
            System.out.println("6. Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo");
            optionMenu = read.nextInt();
                    
            switch(optionMenu){
                
                case 1:
                    String name;
                    System.out.println("Ingrese el nombre de la carta que desea agregar a su coleccion: ");
                    read.nextLine();
                    name = read.nextLine();
                    if (totalCards.containsKey(name) == true){
                        characters.add(name);
                        String newCard = totalCards.get(name);
                        totalCards.remove(name);
                        cards.put(name, newCard);
                        cardNamePlayer.add(name);
                        System.out.println("La carta '" + name + "' se ha agregado a tu coleccion.");
                    }else{
                        System.out.println("La carta que ingresaste no esta dentro del indice.");
                    }
                    break;
                
                case 2:
                    String name2;
                    System.out.println("Ingrese el nombre de la carta de la que desea conocer el tipo: ");
                    read.nextLine();
                    name2 = read.nextLine();
                    System.out.println("La carta '" + name2 + "' es de tipo:" + totalCards.get(name2));
                    break;

                case 3:
                    int countMonster = 0;
                    int countTrap = 0;
                    int countSpells = 0;

                    //Muestra las cartas en la coleccion
                    cards.forEach((nameC,type) -> System.out.println(nameC + " - " + type));

                    //Contar cantidad de cada tipo
                    for (int item = 0; item < cards.size(); item++){
                        if (cards.get(cardNamePlayer.get(item)).equals("Monstruo")){
                            countMonster +=1;      
                        }else if(cards.get(totalCards.get(cardNamePlayer.get(item))).equals("Hechizo")){
                            countTrap +=1;
                        }else if(cards.get(totalCards.get(cardNamePlayer.get(item))).equals("Trampa")){
                            countSpells +=1;
                        }
                    }
                    //Output del resultado
                    System.out.println("En tu coleccion se encuentran: "
                            + "\nMonstruos: " + countMonster
                            + "\nHechizos: " + countTrap
                            + "\nTrampas: " + countSpells);
                    break;
                    
                case 4:

                    String monsterPlayer = " ";
                    String trapPlayer = " "; 
                    String spellPlayer = " ";

                    //Buscar tipo de carta y separarlos
                    for (int i = 0; i < cards.size(); i++){
                        String nameAllPlayer = cardNamePlayer.get(i);
                        String typePlayer = cards.get(nameAllPlayer);

                    switch (typePlayer) {
                        case "Monstruo":
                            monsterPlayer = monsterPlayer + "\n" + nameAllPlayer + " (Monstruo)";
                            break;
                        case "Trampa":
                            trapPlayer = trapPlayer + "\n" + nameAllPlayer + " (Trampa)";
                            break;
                        case "Hechizo":
                            spellPlayer = spellPlayer + "\n" + nameAllPlayer + " (Hechizo)";
                            break;
                        }   
                    }
                    System.out.println(monsterPlayer + trapPlayer + spellPlayer);
                    break;

                case 5:
                    //Output de cartas
                    for (String ab : allCards) {
                        System.out.println(ab);
                    }
                    break;

                case 6:
                    String monster = " ";
                    String trap = " "; 
                    String spell = " ";

                    //Buscar tipo de carta y separarlas
                    for (int i = 0; i < cardName.size(); i++){
                        String nameAll = cardName.get(i);
                        String type = totalCards.get(nameAll);

                    switch (type) {
                        case "Monstruo":
                            monster = monster + "\n" + nameAll + " (Monstruo)";
                            break;
                        case "Trampa":
                            trap = trap + "\n" + nameAll + " (Trampa)";
                            break;
                        case "Hechizo":
                            spell = spell + "\n" + nameAll + " (Hechizo)";
                            break;
                        default:
                            break;
                        }
                    }
                    System.out.println(monster + trap + spell);
            }
            
        }
        
        
    }
    
}
