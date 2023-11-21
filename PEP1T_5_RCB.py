import os
import pickle

salir = True

# Estilo de la letra


class Estilo:
    negrita = "\033[1m"
    subrayo = "\033[4m"
    final = "\033[0m"

# Funciones para las opciones del menú


def altaabon():
    print(f"{Estilo.negrita}{Estilo.subrayo}Alta de factura{Estilo.final}")

    with open("facturas_telf.dat", "rb", encoding='utf8') as f:
        datos = pickle.load(f)
        dicc = {'num_abo' : int(input("Número del abonado: ")),
                'nombre' : input("Nombre: "),
                'valor_fact' : float(input("Valor de la factura: "))}
        datos.append(dicc)

    with open("facturas_telf.dat", "wb", encoding="utf8") as f:
        pickle.dump(datos, f)

    print("Datos del nuevo usuario incorporados al fichero")


def modif():
    print(f"{Estilo.negrita}{Estilo.subrayo}Modificación del valor de factura{Estilo.final}")
    num_abo = int(input("Número del abonado: "))
    with open("facturas_telf.dat", "rb", encoding="utf8") as f:
        datos = pickle.load(f)


def consabon():
    print(f"{Estilo.negrita}{Estilo.subrayo}Consulta facturación abonado{Estilo.final}")
    num_abo = int(input("Número del abonado: "))


def conscomp():
    print(f"{Estilo.negrita}{Estilo.subrayo}Consulta facturación total{Estilo.final}")
    print(f"Facturación total: ")


def eliminar():
    print(f"{Estilo.negrita}{Estilo.subrayo}Eliminar fichero{Estilo.final}")
    os.remove('facturas_telf.dat')
    print('Fichero eliminado')


# Encabezado

cardinal = ('primera', 'segunda', 'tercera', 'cuarta', 'quinta', 'sexta')
print(f'\n\t\t\t{Estilo.negrita}{Estilo.subrayo}PROGRAMA GESTIÓN COMPAÑÍA TELEFÓNICA{Estilo.final}\n')
with open('menu.txt', 'w+', encoding='utf8') as f:
    for i in range(6):
        f.write(f"{i+1}) " + input(f"Introduce el nombre de la {cardinal[i]} opción: ") + "\n")
print()

# Menú de opciones

while salir:

    print(f'\n\t\t\t{Estilo.negrita}{Estilo.subrayo}PROGRAMA GESTIÓN COMPAÑÍA TELEFÓNICA{Estilo.final}\n')
    print(f"\t\t\t\t\t{Estilo.negrita}Menú de opciones{Estilo.final}\n \t\t\t\t\t================\n")

    with open("menu.txt", "r", encoding="utf8") as f:
        for i in range(6):
            print('\t\t', f.readline(), end=" ")

    opciones = input("\t\t\t\t\t\t\tOpción: ")

    if opciones == "1":
        altaabon()

    elif opciones == "2":
        modif()

    elif opciones == "3":
        consabon()

    elif opciones == "4":
        conscomp()

    elif opciones == "5":
        eliminar()

    elif opciones == "6":
        print("Fin del programa")
        salir = False

    else:
        print("¡Eso no es una opción valida!\n")