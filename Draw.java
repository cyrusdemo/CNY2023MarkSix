import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Draw{

    public static void main(String[] args){
        List<Integer> output = new ArrayList<>();
        
        //init values
        List<Integer> pools = new ArrayList<>(Arrays.asList(
            3,10,14,20,
            8,13,29,45,
            7,15,25,33,
            12,23,47,49,
            9,28,26,2,
            11,17,22
        ));

        //Check duplicate
        if(pools.stream().distinct().count() != pools.size()){
            System.out.println("Duplicate found!");
            return;
        }
        
        //Check size
        if(pools.size() != (8+8+3+4)){//allup 8 x 2; banker 3 + leg 4
            System.out.println("Size not match!");
            return;
        }

        //Draw allup x 2
        try{
            for(int i=1;i<=2;i++){
                for(int x=1;x<=8;x++){
                    Random rand = new Random(System.currentTimeMillis());
                    int idx = rand.nextInt(pools.size());
                    output.add(pools.remove(idx));
                }
                //Display the output
                printSortedList("Allup " + i + " : ", output);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        //Draw banker x 1 (3 + 4)
        try{
            for(int i=1;i<=2;i++){
                while(true){
                    Random rand = new Random(System.currentTimeMillis());
                    int idx = rand.nextInt(pools.size());
                    output.add(pools.remove(idx));
                    if(
                        (i == 1 && output.size() == 3)//banker (3)
                        ||
                        (i == 2 && output.size() == 4)//leg (4)
                    ){
                        printSortedList((i == 1 ? "Banker" : "Leg") + " : ", output);
                        break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        //Check any remaining
        if(pools.size() != 0){//allup 8 x 2; banker 3 + leg 4
            System.out.println("Remaining found!");
            return;
        }
    }//end of main

    private static void printSortedList(String prefix, List<Integer> output){
        //Display the output which is sorted
        System.out.println(prefix + output.stream().sorted().map(Objects::toString).collect(Collectors.joining(",")));
        //Clean up the output
        output.clear();
    }

}//end of class