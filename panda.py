import pandas as pd
from entidades import Produto
from dao import ProdutoDao


#musicas = pd.read_csv('./bandas.csv')
#print(musicas.head())
#print(type(musicas))

#musicas_sem_cabecalho = pd.read_csv('./bandas.csv', header=None)

#print(musicas.head())

#Esse comando pega todas as informações de todas as colunas do index 0
#print(musicas.iloc[0])
#musica = musicas.iloc[0]
#print(musica[0])
#print(musica[1])
#print(musica[2])
#print(musica[3])

#teste = Produto(musica[0], musica[1], musica[2])



#Esse comando pega todas as informações somente da primeira coluna
#print(musicas.iloc[:,0])

#Esse pega as 3 primeiras informações da primeira coluna
#print(musicas.iloc[:3,0])

lista = pd.read_csv('./lista.csv')
#print(lista)
#print(lista.iloc[499])

#produto = Produto()
#item = lista.iloc[1] 
#print(lista.iloc[0])
#print(item[0])
#print('Nome: ', item[1])
#print('Categoria:',item[2])
#print('Preco:',item[3])

banco = ProdutoDao()

for i in range(len(lista)):
    item = lista.iloc[i]
    produto = Produto(item[1], item[3], item[2])
    banco.save(produto)

    


