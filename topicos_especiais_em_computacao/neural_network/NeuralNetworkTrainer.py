import csv
import numpy as np
import time
import datetime
from NeuralNetwork import NeuralNetwork

evadidos = np.genfromtxt('dados_alunos_evadidos.csv', delimiter=',')
ativos = np.genfromtxt('dados_alunos_ativos.csv', delimiter=',')
alunos = np.concatenate((evadidos, ativos), axis=0)
# Caso eu rodo separado cada grupo de informações, a resposta dende a ser mais corerente com o esperado,
# contudo, rodando os dois grupos juntos na rede, a resposta é tendenciosa para um dos grupos.

x = np.zeros(shape=(0, 30))
y = np.zeros(shape=(0, 2))
for aluno in alunos:
	y = np.concatenate((y, [[aluno[30], aluno[31]]]), axis=0);
	aluno = np.delete(aluno, 31)
	aluno = np.delete(aluno, 30)
	x = np.concatenate((x, [aluno]), axis=0)

nn = NeuralNetwork(x, y)

print(datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S'))
for i in range(100000):
	nn.feedforward()
	nn.backprop()
print(datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S'))

print(nn.output)