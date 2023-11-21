import sys
cadena = sys.argv[1:]
cadena = " ".join(cadena)
cadena = cadena.upper()
salir = True


class estilo:
    negrita = "\033[1m"
    subrayo = "\033[4m"
    final = "\033[0m"


def pnormal(cadena):

    # Declaración variables

    cont1 = 0
    cont2 = 0
    total = 0

    # Controlamos aqui que en el caso de que el usuario no agregue el '.' final se le agregue automaticamente

    if cadena[-1] != '.':
        cadena = cadena + '.'

    # La variable lista guardara la cadena separada por palabras

    lista = cadena.split()

    # Reemplazamos en la cadena los '.' por la palabra 'STOP'

    cadena = cadena.replace(".", " STOP")

    # Agregamos al final de la cadena la palabra 'STOP' para indicar que es el '.' final

    if cadena[-1]:
        cadena += "STOP"

    # Contamos las palabra de la lista

    resultado = len(lista)

    # Creamos un bucle para recorrer las letras de las palabras

    for i in lista:

        # Con este metodo hacemos que no se cuenten los '.' como si fuera una letra más

        palabra_sp = i.rstrip('.')

        num_letras = len(palabra_sp)

        # Creamos un contador que vaya contando el numero de palabras con más de 5 letras o menos

        if num_letras > 5:
            cont2 += 1
        else:
            cont1 += 1

        # Calculo simple para crear el pago total

        total = (cont2 * 0.5) + (cont1 * 0.25)

    print(f"La cadena contiene {resultado} palabras, de las cuales {cont2} tienen más de 5 letras.")
    print(f"Por tanto, al precio de 0,25€/palabra tenemos {cont1} y a 0,50€/palabra hay otras {cont2}.")
    print(f"Total: {total}€")

    print("\nMensaje enviado:", cadena)


def pred(cadena):

    # Declaración variables
    manolo = ""
    cont1 = 0

    # Control del punto final
    if cadena[-1] != '.':
        cadena += '.'

    # Separa las palabras una a una
    cadena2 = cadena.split()

    # Recoge el número de palabras de la cadena
    resultado2 = len(cadena2)

    # Reemplaza los puntos por la palabra STOP
    cadena2 = cadena.replace(".", " STOP")

    # Volvemos a separar la cadena por palabras pero esta vez con las palabras STOP
    lista2 = cadena2.split()

    # Agregamos un STOP al final
    if cadena2[-1]:
        cadena2 += " STOP"

    for i in lista2:

        # Este metodo hace que no cuente las palabras STOP
        palabra_sp = i.rstrip('STOP')
        # Contar el numero de letras de cada palabra de la cadena
        num_letras2 = len(palabra_sp)
        # Comparar si la palabra mide más o menos de 5 caracteres y hacer un recuento
        if num_letras2 > 5:
            cont1 += 1
            i = i[:5]
            i += "@"
        manolo += " " + i

    # Agregar a la nueva cadena un STOP final
    if manolo[-1]:
        manolo += "STOP"

    # Cuenta del precio total del mensaje
    total2 = resultado2 * 0.25

    print(f"La cadena contiene {resultado2} palabras, de las cuales {cont1} tenían más de "
          f"5 letras pero se han recortado.")
    print(f"Por tanto, el mensaje se envía al precio de 0.25€/palabra.")
    print(f"Precio Total: {total2}€")
    print(f"\nMensaje enviado:\n{manolo}")


def pmorse(cadena):

    cadena2 = cadena.upper()

    # Declaración variables
    rayas = 0
    puntos = 0
    texto_codif = ""

    # Declaramos el diccionario a utilizar
    diccionario_morse = {
        "A": ".-", "B": "-...", "C": "-.-.", "D": "-..", "E": ".", "F": "..-.",
        "G": "--.", "H": "....", "I": "..", "J": ".---", "K": "-.-", "L": ".-..",
        "M": "--", "N": "-.", "O": "---", "P": ".--.", "Q": "--.-",
        "R": ".-.", "S": "...", "T": "-", "U": "..-", "V": "...-", "W": ".--",
        "X": "-..-", "Y": "-.--", "Z": "--..", ".": ".-.-.-",
        "0": "-----", "1": ".----", "2": "..---", "3": "...--", "4": "....-",
        "5": ".....", "6": "-....", "7": "--...", "8": "---..", "9": "----."
    }

    # Bucle en el cual compara caracter a caracter para ver si esta en el diccionario
    # Si esta en el diccionario lo agrega a la nueva cadena sino, agrega ???
    for c in cadena2:
        if c in diccionario_morse:
            texto_codif += diccionario_morse[c]
        elif c == " ":
            texto_codif += " / "
        elif c not in diccionario_morse:
            texto_codif += "???"

    # Contador de - y .
    for c in texto_codif:
        if c == "-":
            rayas += 1
        elif c == ".":
            puntos += 1

    # Calcular el precio de los - y .
    # Calcular el precio total de ambos
    p_rayas = rayas * 0.01
    p_puntos = puntos * 0.005
    resultado_rp = p_rayas + p_puntos

    print(f"La cadena convertida a código Morse tiene {puntos} puntos (0.005€/punto) y {rayas} rayas (0.01€/raya)")
    print(f"Por tanto, el mensaje se envía al precio de {resultado_rp}€ ({p_puntos}€ y {p_rayas}€)")
    print(f"\nMensaje enviado: \n{texto_codif}")


# Menú del programa
print(f"{estilo.negrita}\n\t\t\t\t\t\t\t{estilo.subrayo}PROGRAMA OPTIMIZACIÓN TELEGRAMA{estilo.final}")
print(f"\nCadena recibida: {cadena.upper()}")
while salir:
    print(f"\n\t\t\t\t\t\t\t{estilo.negrita}Menú de Opciones{estilo.final}")
    print("\t\t\t\t\t\t\t================")
    print("\n\t\t\t\t1) Envío con precio normal")
    print("\t\t\t\t2) Envío con precio reducido")
    print("\t\t\t\t3) Envío barato en código Morse")
    print("\t\t\t\t4) Salir\n")

    opciones = input("\t\t\t\t\t\t\tOpción: ")

    if opciones == "1":
        print()
        pnormal(cadena)
        print()

    elif opciones == "2":
        print()
        pred(cadena)
        print()

    elif opciones == "3":
        print()
        pmorse(cadena)
        print("")

    elif opciones == "4":
        print("Fin del programa")
        salir = False

    else:
        print("¡Eso no es una opción valida!\n")