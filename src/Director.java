import java.util.Stack;

public class Director extends Thread{
	
	public static int directores;
	public static int identificador;
	public static Stack pila = new Stack();
	
	public Director(String msg) {
		super(msg);
	}
	
	public Director() {
		
	}
	
	public void recibirDirector(int director) {
		
		this.directores = director;
		
	}
	
	public void recibirLlamada(String telefono, String duracion) {
		
		String llamada = telefono+"|"+duracion;
		
		pila.push(llamada);
		
	}
	
	public void run() {
		
		String [] vectorDirectores = new String [directores];
		int [] vectorCantidad = new int [directores];
		
		for (int i = 0; i < vectorDirectores.length; i++) {
			
			vectorDirectores[i] = "LIBRE";
			
		}
		
		while(true) {
			
			try {
				
				for (int i = 0; i < vectorDirectores.length; i++) {
					
					if (vectorDirectores[i].equals("OCUPADO")) {
						
						identificador = 5;
						
					}else {
						
						i = vectorDirectores.length;
						identificador = 0;
					}
					
				}
				
				if(identificador == 5) {
					
					System.out.println("Todos los directores estan llenos ");
					
				}
					
				for (int i = 0; i < vectorDirectores.length; i++) {
					
					if(pila.empty() == false ) {
					
					if(vectorDirectores[i].equals("LIBRE")) {
						
						String llamada2 = (String) pila.pop();
						
						String[] parts = llamada2.split("\\|");    
						
						String primero = parts[0];
						String segundo = parts[1];
						
						int auxSegundo = Integer.parseInt(segundo);
						
						vectorCantidad[i] = auxSegundo * 1000;
						
						int incremental = i+1;
						
						System.out.println("El director "+incremental+" a iniciado la llamada "+primero);
						
						vectorDirectores[i] = "OCUPADO";
						
						
					}else if(vectorDirectores[i].equals("OCUPADO")){
						
						if(System.currentTimeMillis() > vectorCantidad[i]) {
							
							int incremental = i+1;
							
							System.out.println("El director "+incremental+" se a liberado");
							vectorDirectores[i] = "LIBRE";
							
						}
						
					}
					
				}else {
					
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
