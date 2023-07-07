import java.util.Scanner;

public class Llamada extends Thread{
	
	public static int telefono;
	public static int tiempo;
	
	
	public Llamada(String msg) {
		super(msg);
	}
	
	public void run() {
		
		while (true) {
			
		try {
			
			
			
			Operador objOperador = new Operador();

			telefono =(int) (Math.random() * 11111);
			
			tiempo =(int) (Math.random() * 10);
			
			if(tiempo!=0) {
				
				objOperador.recibirLlamada(telefono, tiempo);
				
			}
			
			sleep(1000);//8000
			
		} catch (InterruptedException e) {
			
			System.out.println("A ocurrido un error");
			e.printStackTrace();
		}
		
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int operador = 0;
		int supervisor = 0;
		int director = 0;
		
		System.out.println("Ingrese el numero de operadores ");
		operador = sc.nextInt();
		
		Operador objOperador = new Operador();
		objOperador.recibirOperadores(operador);
		
		System.out.println("Ingrese el numero de supervisores ");
		supervisor = sc.nextInt();
		
		Supervisor objSupervisor = new Supervisor();
		objSupervisor.recibirSupervisores(supervisor);
		
		System.out.println("Ingrese el numero de directores ");
		director = sc.nextInt();
			
		Director objDirector = new Director();
		objDirector.recibirDirector(director);
		
		Thread hilo = new Llamada("Proceso 1");
		
		Thread hilo2 = new Operador("Proceso 2");
		
		Thread hilo3 = new Supervisor("Proceso 3");
		
		Thread hilo4 = new Director("Proceso 4");
		
		try {
			
			hilo.start();
			hilo2.sleep(3000);
			hilo2.start();
			hilo3.sleep(3000);
			hilo3.start();
			hilo4.sleep(3000);
			hilo4.start();

		} catch (Exception e) {
			
			System.out.println(e);
		}

	}

}
