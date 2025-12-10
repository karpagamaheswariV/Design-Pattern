
public class Main
{
	public static void main(String[] args) {
		Config instance1=Config.getInstance();
		Config instance2=Config.getInstance();
		if(instance2==instance1){
		    System.out.println("Both the instance are same or equal");
		}
		else{
		    System.out.println("Both the instance are different");
		}
	}
}

class Config{
    private static Config instance=null;
    private Config(){
        
    }
    public static Config getInstance(){
        if(instance==null){
            instance=new Config();
        }
        return instance;
    }
}
