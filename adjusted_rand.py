import sys
import math

from sklearn import metrics

entrada1 = sys.argv[1]
entrada2 = sys.argv[2]

arquivo1 = open(entrada1, 'r')
arquivo2 = open(entrada2, 'r')

lista1 = []
lista2 = []

lista_teste = []

string_teste = arquivo1.readline()

'''
print(string_teste)



print(string_teste[:1])

print(lista_teste)
print(lista_teste[1][0])
'''
while True:
    if len(string_teste) == 0:
        break
    

    lista_teste = string_teste.split('\t')
    lista1.append(int(lista_teste[1][0]))
    string_teste = arquivo1.readline()


string_teste = arquivo2.readline()
while True:
    if len(string_teste) == 0:
        break
    
    lista_teste = string_teste.split('\t')

    '''print(lista_teste)'''

    lista2.append(int(lista_teste[1][1]))
    string_teste = arquivo2.readline()

print(metrics.adjusted_rand_score(lista1, lista2))
