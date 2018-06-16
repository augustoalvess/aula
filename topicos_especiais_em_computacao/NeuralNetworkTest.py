import numpy as np
from NeuralNetwork import NeuralNetwork

#Preciso montar uma lista com os dados de alunos já evadidos e não evadidos
x = np.array([
	[0, 0, 1],
	[0, 1, 1],
	[1, 0, 1],
	[1, 1, 1]
])

#Todos os resultados dos alunos deverão ser o resultado definido em y
y = np.array([
	[0],
	[1],
	[1],
	[0]
])

nn = NeuralNetwork(x=x, y=y)

#Executa 1500 iterações
for i in range(1500):
	nn.feedforward()
	nn.backprop()

print(nn.output)