import json
import auxiliar 
with open('sols/g128.json', 'r') as f:
    datos_grafo = json.load(f)

with open('solucion.json', 'r') as f:
    colores_asignados = json.load(f)

print("Renderizando el mapa coloreado...")
auxiliar.dibujar_mapa_coloreado(datos_grafo, colores_asignados)