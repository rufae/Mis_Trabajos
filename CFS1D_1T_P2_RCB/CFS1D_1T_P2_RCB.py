import sys
import json
import random
cadena = sys.argv[1:]
cadena = " ".join(cadena)

# Variables
intentos = 0
conj = set()
lista_intentos = []
boole = True


# Clase estilo para cambiar el diseño del encabezado
class Estilo:
    negrita = "\033[1m"
    subrayo = "\033[4m"
    final = "\033[0m"
    cursiva = "\033[3m"


# Encabezado
print(f"\n\t\t\t\t\t\t{Estilo.negrita}{Estilo.subrayo}{cadena}{Estilo.final}\n")

# Lectura del fichero json
with open("CFS1D_1T_P2_RCB/letreros.json", "r", encoding="utf8") as f:
    info = json.load(f)

# Insertando las frases en el diccionario
    for i in info:
        print(f"Insertando el letrero en el diccionario: {info[i]}")

    diccionario = {
        "Programa": info["letrero1"],
        "Intentos": info["letrero2"],
        "Introduce": info["letrero3"],
        "Pequeno": info["letrero4"],
        "Grande": info["letrero5"],
        "Correcto": info["letrero6"],
        "Incorrecto": info["letrero7"]
    }

print(f"\n{Estilo.negrita}El diccionario ya esta completo{Estilo.final}")
print(f"\n\t\t\t\t\t\t{Estilo.negrita}(.......){Estilo.final}")


# Funcion que genera un número aleatorio
def numaleat():

    numero_aleatorio = random.randint(1, 40)
    return numero_aleatorio


print()
print(info["letrero1"])
print(info["letrero2"])

numero_aleatorio = numaleat()

# Bucle para el juego
while boole and intentos < 6:

    usuario = int(input("\nIntroduce el número que estas pensando: "))

    if usuario < 0 or usuario > 40:
        print("Deber meter un número entre 0 y 40.")

    else:
        lista_intentos.append(usuario)
        intentos += 1

        if intentos > 5:
            print(info["letrero7"])
            boole = False

        elif usuario > numero_aleatorio:
            print(info["letrero4"])

        elif usuario < numero_aleatorio:
            print(info["letrero5"])

        elif usuario == numero_aleatorio:
            print(info["letrero6"])
            print(f"¡Has necesitado {intentos} intentos!")
            boole = False


lista_intentos = sorted(set(lista_intentos))
lista_intentos = ", ".join(map(str, lista_intentos))

print(f"\nLa lista tiene como números tecleados: {lista_intentos}")
