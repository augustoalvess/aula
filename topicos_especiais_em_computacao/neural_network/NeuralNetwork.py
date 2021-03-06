import numpy as np

def sigmoid(x):
    return 1.0 / (1+ np.exp(-x))

def sigmoid_derivative(x):
    return x * (1.0 - x)

class NeuralNetwork:

    def __init__(self, x, y):
        self.input = x
        self.weights1 = np.random.rand(self.input.shape[1], 35)
        self.weights2 = np.random.rand(35, 2)
        self.y = y
        self.output = np.zeros(self.y.shape)

    def feedforward(self):
        self.layer1 = sigmoid(np.dot(self.input, self.weights1))
        self.output = sigmoid(np.dot(self.layer1, self.weights2))

    def backprop(self):
        # application of the chain rule to find derivative of the loss function with respect to weights2 and weights1
        loss = (2 * (self.y - self.output) * sigmoid_derivative(self.output));
        d_weights2 = np.dot(self.layer1.T, loss)
        d_weights1 = np.dot(self.input.T, (np.dot(loss, self.weights2.T) * sigmoid_derivative(self.layer1)))

        # update the weights with the derivative (slope) of the loss function
        self.weights1 += d_weights1
        self.weights2 += d_weights2

