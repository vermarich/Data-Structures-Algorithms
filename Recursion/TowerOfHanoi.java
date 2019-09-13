
class TowerOfHanoi{

    public void TOH(int n, String from, String aux, String to){
        if(n==1) {
            System.out.println("Moving disc " + n + " from "+ from + " to " + to);
            return;
        }
        else {
            TOH(n-1, from, to, aux);
            System.out.println("Moving disc " + n +  " from "+ from + " to " + to);
            TOH(n-1, aux, from, to);
        }
    }

    public static void main(String[] args){

        TowerOfHanoi obj = new TowerOfHanoi();
        obj.TOH(3,"A","B","C");

    }

}