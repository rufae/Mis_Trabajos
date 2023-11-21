Algoritmo PEP1T1
	sumacontaminantes = 0
	max = 0
	
	imprimir 'Introduzca el dato del coche 1'
	leer contaminantes
	min = contaminantes
	
	Para I <- 2 Hasta 10 con paso 1 Hacer
		imprimir 'Introduzca el dato del coche ' I
		leer contaminantes
		si contaminantes < min
			entonces min = contaminantes
		FinSi
		
		si contaminantes > max
			Entonces max = contaminantes
		FinSi
		
		sumacontaminantes <- contaminantes + sumacontaminantes
		
	FinPara
	
	promedio <- sumacontaminantes/10
	
	Imprimir 'El promedio de contaminación es de: ' promedio
	Imprimir 'Siendo el mayor: ' max
	Imprimir 'Siendo el menor: ' min
FinAlgoritmo