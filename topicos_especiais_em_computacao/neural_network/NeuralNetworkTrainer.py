import csv
import numpy as np
import time
import datetime
from NeuralNetwork import NeuralNetwork

evadidos = np.genfromtxt('dados_alunos_evadidos.csv', delimiter=',')
ativos = np.genfromtxt('dados_alunos_ativos.csv', delimiter=',')
alunos = np.concatenate((evadidos, ativos), axis=0)
np.random.shuffle(alunos);

x = np.zeros(shape=(0, 30))
y = np.zeros(shape=(0, 2))
for aluno in alunos:
	y = np.concatenate((y, [[aluno[30], aluno[31]]]), axis=0);
	aluno = np.delete(aluno, 31)
	aluno = np.delete(aluno, 30)
	x = np.concatenate((x, [aluno]), axis=0)

nn = NeuralNetwork(x, y)

# Treinamento da rede
for i in range(1000000):
	nn.feedforward()
	nn.backprop()
#print(nn.output)

# Teste da rede
x = np.array([
	[0,1,0,0,1,0,0,0,0,1,1,0,0,1,0,0,1,1,1,1,0,1,1,1,0,1,0,0,0,0], # Aluno evadido
	[1,1,1,0,1,0,0,0,0,1,1,0,0,0,1,0,1,1,1,1,1,0,1,1,1,0,0,0,1,0] # Aluno ativo
])
nn.input = x;
nn.feedforward()
print(nn.output)

