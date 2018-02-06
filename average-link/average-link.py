import sys
import math

''' ------------------------------------------------------------------------- '''
def imprime_matriz():
	print("\n")
	for i in matriz:
		for j in i:
			print("%f\t" % j, end='')
		print(clusters[matriz.index(i)], end='\t\t')
		print("")
	print("\n")


''' ------------------------------------------------------------------------- '''
def distancia(obj1, obj2):
	x1 = objetos[obj1][0]
	x2 = objetos[obj2][0]
	y1 = objetos[obj1][1]
	y2 = objetos[obj2][1]
	return math.sqrt(math.pow(x1-x2, 2)+math.pow(y1-y2, 2))


''' ------------------------------------------------------------------------- '''
def calcular_distancia(c1, c2):
	menor_valor = 999999999
	for obj1 in c1:
		for obj2 in c2:
			menor_valor = min(distancia(obj1, obj2), menor_valor)

	return menor_valor


''' ------------------------------------------------------------------------- '''
def menor_distancia():
	i = j = 0
	min_i = min_j = 0
	menor_valor = 99999999
	qtd = len(matriz)
	for i in range(qtd):
	    for j in range(qtd):
	        if (i > j) and (matriz[i][j] < menor_valor):
	            menor_valor = matriz[i][j]
	            min_i = i
	            min_j = j
	return [min_i, min_j]


''' ------------------------------------------------------------------------- '''
def unir_clusters(i, j):
	clusters[j].extend(clusters[i])
	del clusters[i]


''' ------------------------------------------------------------------------- '''

def distancia_media(c1, c2):
    ret = 0
    n1 = len(c1)
    n2 = len(c2)
    for obj1 in c1:
        
        for obj2 in c2:
        
            ret = ret + distancia(obj1,obj2)
    ret = ret / (n1 * n2)    
    return ret
    



nome_arquivo = sys.argv[1]
arquivo = open(nome_arquivo, 'r')
kMin = int(sys.argv[2])
kMax = int(sys.argv[3])

nome_arquivo_saida = nome_arquivo.split(".")[0] + "Real_"

objetos = {}
clusters = []

linha = arquivo.readline()
while True:
	linha = arquivo.readline()
	if len(linha) == 0:
		break

	linha = linha.split('\n')
	linha = linha[0].split('\t')
	objetos.update({linha[0] : [float(linha[1]), float(linha[2])]})
	clusters.append([linha[0]])

while len(clusters) > kMin:
	qtd = len(clusters)
	matriz = [
		[distancia_media(clusters[i], clusters[j]) if i > j else 0 for j in range(qtd)] for i in range(qtd)
	]
	min_dist = menor_distancia()
	unir_clusters(min_dist[0], min_dist[1])

	if len(clusters) == kMax:
		arquivo_saida = open(nome_arquivo_saida + str(kMax) + ".clu", "w")

		for cluster in clusters:
			for item in cluster:
				arquivo_saida.write(item + " \t " + str(clusters.index(cluster)) + "\n")

		kMax = kMax - 1
