import java.util.Stack;

public class Supervisor extends Thread{
	
	public static int supervisores;
	public static int identificador;
	public static Stack pila = new Stack();
	
	public Supervisor(String msg) {
		super(msg);
	}
	
	public Supervisor() {
		
	}
	
	public void recibirSupervisores(int supervisor) {
	
		this.supervisores = supervisor;
		
	}
	
	public void recibirLlamada(String telefono, String duracion) {
		
		String llamada = telefono+"|"+duracion;
		
		pila.push(llamada);
		
	}
	
	public void run() {
		
		String [] vectorSupervisores = new String [supervisores];
		int [] vectorCantidad = new int [supervisores];
		
		for (int i = 0; i < vectorSupervisores.length; i++) {
			
			vectorSupervisores[i] = "LIBRE";
			
		}
		
		while(true) {
			
			try {
				
				for (int i = 0; i < vectorSupervisores.length; i++) {
					
					if (vectorSupervisores[i].equals("OCUPADO")) {
						
						identificador = 5;
						
					}else {
						
						i = vectorSupervisores.length;
						identificador = 0;
					}
					
				}
								
				if(identificador == 5) {
					
					System.out.println("Todos los supervisores estan llenos ");
					
					Director objDirector = new Director();
					
					if(pila.empty() == false) {
						
					String llamada2 = (String) pila.pop();
					
					String[] parts = llamada2.split("\\|");    
					
					String primero = parts[0];
					String segundo = parts[1];
					
					objDirector.recibirLlamada(primero, segundo);
					}
					
				}
					
				for (int i = 0; i < vectorSupervisores.length; i++) {
					
					if(pila.empty() == false ) {
					
					if(vectorSupervisores[i].equals("LIBRE")) {
						
						String llamada2 = (String) pila.pop();
						
						String[] parts = llamada2.split("\\|");    
						
						String primero = parts[0];
						String segundo = parts[1];
						
						int auxSegundo = Integer.parseInt(segundo);
						
						vectorCantidad[i] = auxSegundo * 1000;
						
						int incremental = i+1;

						System.out.println("El supervisor "+incremental+" a iniciado la llamada "+primero);
						
						vectorSupervisores[i] = "OCUPADO";
						
						//contador = contador + 1;
						
						
					}else if(vectorSupervisores[i].equals("OCUPADO")){
						
						if(System.currentTimeMillis() > vectorCantidad[i]) {
							
							
							//System.out.println("Este es el currentMillis "+System.currentTimeMillis());
							int incremental = i+1;
							
							System.out.println("El supervisor "+incremental+" se a liberado");
							vectorSupervisores[i] = "LIBRE";
							
							//contador = contador - 1;
							
							//System.out.println("Este es el valor de operadores "+operadores);
							
						}
						
					}
					
				}else {
					
					//System.out.println("No hay llamadas en cola");
					
				}
				}
					
				sleep(8000);//6000
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
	}
	
}
