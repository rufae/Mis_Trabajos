cont1 = 0
cont2 = 0
total = 0

#La variable cadena guardara el mensaje a enviar

cadena = input("Teclea el mensaje: ")

#Controlamos aqui que en el caso de que el usuario no agregue el '.' final se le agregue automaticamente

if cadena[-1] != '.':
    cadena = cadena + '.'

#Se enseña la cadena arreglada

print("Cadena tecleada: " + cadena)

#La variable lista guardara la cadena separada por palabras

lista = cadena.split()

#Reemplazamos en la cadena los '.' por la palabra 'STOP'

cadena = cadena.replace(".","STOP")

#Agregamos al final de la cadena la palabra 'STOP' para indicar que es el '.' final

if cadena[-1]:
    cadena += "STOP"

#Enseñamos la cadena nueva

print("Mensaje a enviar: " + cadena)

#Contamos las palabra de la lista

resultado = len(lista)

#Creamos un bucle para recorrer las letras de las palabras

for i in lista:

#Con este metodo hacemos que no se cuenten los '.' como si fuera una letra más

    palabra_sp = i.rstrip('.')

    num_letras = len(palabra_sp)

#Creamos un contador que vaya contando el numero de palabras con más de 5 letras o menos

    if num_letras > 5:
        cont2 += 1
    else:
        cont1 += 1

#Calculo simple para crear el pago total

    total = (cont2 * 0.5)+(cont1 * 0.25)

print(f"La cadena contiene {resultado} palabras, de las cuales {cont2} tienen más de 5 letras.")
print(f"Por tanto, al precio de 0,25€/palabra tenemos {cont1} y a 0,50€/palabra hay otras {cont2}.")
print(f"Total: {total}€")
