import java.util.Stack;

public class Operador extends Thread{
	
	public static Stack pila = new Stack();
	
	
	public int telefono;
	public int tiempo;
	public static int operadores;
	public static int contador;
	public static int identificador;
	
		
	public Operador(String msg) {
		super(msg);
	}
	
	public Operador() {
		
	}
	
	public Operador(int tel, int duracion) {
		
		telefono = tel;
		tiempo = duracion;
		
		
	}
	
	public void recibirOperadores(int operador) {
		
		this.operadores = operador;
		
	}
	
	public void recibirLlamada(int tel, int duracion) {
		
		Operador objOperador;
		
		objOperador = new Operador(tel, duracion);
		
		int [] vector = new int [2]; 
		
		vector[0] = tel;
		vector[1] = duracion;
		
		String telefono = String.valueOf(tel);
		String tiempo = String.valueOf(duracion);
		
		String llamada = telefono+"|"+duracion;
		
		pila.push(llamada);	
				
		System.out.println("Se a puesto la llamada "+telefono+" en cola, con duración de "+tiempo);
		
	}
		
	public void run() {
		
		String [] vectorOperadores = new String [operadores];
		int [] vectorCantidad = new int [operadores];
		
		for (int i = 0; i < vectorOperadores.length; i++) {
			
			vectorOperadores[i] = "LIBRE";
			
		}
		
		while(true) {
		
		try {
			
			for (int i = 0; i < vectorOperadores.length; i++) {
				
				if (vectorOperadores[i].equals("OCUPADO")) {
					
					identificador = 5;
					
				}else {
					
					i = vectorOperadores.length;
					identificador = 0;
				}
				
			}
				
			if(identificador == 5) {
				
				Supervisor objSupervisor = new Supervisor();
				
				System.out.println("Todos los operadores estan llenos ");
				
				String llamada2 = (String) pila.pop();
				
				String[] parts = llamada2.split("\\|");    
				
				String primero = parts[0];
				String segundo = parts[1];
				
				objSupervisor.recibirLlamada(primero, segundo);
				
				
				
			}
						
			for (int i = 0; i < vectorOperadores.length; i++) {
				
				if(pila.empty() == false ) {
				
				if(vectorOperadores[i].equals("LIBRE")) {
					
					String llamada2 = (String) pila.pop();
					
					String[] parts = llamada2.split("\\|");    
					
					String primero = parts[0];
					String segundo = parts[1];
					
					int auxSegundo = Integer.parseInt(segundo);

					vectorCantidad[i] = auxSegundo * 1000;
					
					int incremental = i+1;
					
					System.out.println("El operador "+incremental+" a iniciado la llamada "+primero);
					
					vectorOperadores[i] = "OCUPADO";
					
					contador = contador + 1;
					
					
				}else if(vectorOperadores[i].equals("OCUPADO")){
					
					if(System.currentTimeMillis() > vectorCantidad[i]) {
						
						int incremental = i+1;
						System.out.println("El operador "+incremental+" se a liberado");
						vectorOperadores[i] = "LIBRE";
						
						contador = contador - 1;
						
						
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
