import csv
import numpy as np
from NeuralNetwork import NeuralNetwork

evadidos = np.genfromtxt('dados_alunos_evadidos.csv', delimiter=',')
ativos = np.genfromtxt('dados_alunos_ativos.csv', delimiter=',')
alunos = np.concatenate((evadidos, ativos), axis=0)

x = np.zeros(shape=(0, 30))
y = np.zeros(shape=(0, 1))
for aluno in alunos:
	y = np.concatenate((y, [[aluno[30]]]), axis=0);
	aluno = np.delete(aluno, 30)
	x = np.concatenate((x, [aluno]), axis=0)

nn = NeuralNetwork(x, y)

for i in range(5000):
	nn.feedforward()
	nn.backprop()

print(nn.output)