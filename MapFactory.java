import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase Map Factory para seleccionar el tipo de mapa a implementar utilizando el patron de diseno Factory
 */
public class MapFactory {
    /**
     * @param option devuelve un int
     * @return si escoje 1: HashMap, 2: TreeMap, 3: LinkedHashMap, nada: null
     */
    //Metodo que valida el tipo de mapa a implementar
    public static Map ChooseMap(int option){
        if (option == 1){
            return new HashMap();
        }else if (option == 2){
            return new TreeMap();
        }else if (option == 3){
            return new LinkedHashMap();
        }
        return null;
    }
    
}
