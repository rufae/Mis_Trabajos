from tkinter import *
from tkinter import filedialog
from pytube import YouTube

# Configuración de la ventana principal
root = Tk()
root.geometry('800x300')
root.title('Rafael Castaño Barroso')
root.configure(bg='#f2f2f2')

# Título principal
Label(root, text='Descargador de YouTube para el Guapetón', font=('Arial', 24, 'bold'), bg='#f2f2f2').place(relx=0.5, rely=0.1, anchor=CENTER)

# Etiqueta y campo de entrada para el enlace de YouTube
Label(root, text='Pega el enlace aquí:', font=('Arial', 14), bg='#f2f2f2').place(relx=0.3, rely=0.3, anchor=E)
link = StringVar()
Entry(root, width=50, textvariable=link, font=('Arial', 12)).place(relx=0.35, rely=0.3, anchor=W)

# Función para descargar el video
def Downloader():
    try:
        url = YouTube(str(link.get()))
        video = url.streams.get_highest_resolution()
        filepath = filedialog.asksaveasfilename(defaultextension=".mp4", filetypes=[("Video files", "*.mp4")])
        if filepath:  # Si el usuario no cancela el diálogo de guardar
            video.download(filepath)
            Label(root, text='¡DESCARGADO!', font=('Arial', 14, 'italic'), bg='#f2f2f2', fg='red').place(relx=0.5, rely=0.8, anchor=CENTER)
    except Exception as e:
        Label(root, text='Error al descargar el video', font=('Arial', 14, 'italic'), bg='#f2f2f2', fg='red').place(relx=0.5, rely=0.8, anchor=CENTER)
        print(e)

# Botón para iniciar la descarga
Button(root, text='DESCARGAR', font=('Arial', 14), bg='#FF0000', fg='white', padx=10, pady=5, command=Downloader).place(relx=0.5, rely=0.5, anchor=CENTER)

# Ejecución de la aplicación
root.mainloop()
