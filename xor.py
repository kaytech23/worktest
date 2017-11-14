import numpy as np

def sigmoid(x):
	return 1/(1 + np.exp(-x))
	
def sigmoid_prime(x):
	return x * (1 - x)
	
	
def relu(x):
	x[x < 0] = 0
	return x
	
def relu_prime(x):
	x[x < 0] = 0
	x[x > 0] = 1
	return x
	
np.random.seed(1)

X = np.array([[1,1], [1,0], [0,1], [0,0]])
y = np.array([[0, 0], [1, 1], [1, 1], [0,0]])


input_size = 2
h1_size = 3
h2_size = 10
output_size = 2

L = 1

sigma = 2
mu = -1;

w1 = sigma * np.random.random((input_size, h1_size)) + mu
b1 = sigma * np.random.random((1, h1_size)) + mu
w2 = sigma * np.random.random((h1_size, h2_size)) + mu
b2 = sigma * np.random.random((1, h2_size)) + mu
wy = sigma * np.random.random((h2_size, output_size)) + mu
by = sigma * np.random.random((1, output_size)) + mu


epoch = 0;

while epoch < 100000:

	# forward pass
	
	z1 = X.dot(w1) + b1
	a1 = sigmoid(z1)
	z2 = a1.dot(w2) + b2
	a2 = relu(z2)
	zy = a2.dot(wy) + by
	ay = sigmoid(zy)
	
	#print("output: ", ay)
	
	# back prop
	
	error = 0.5 * (y - ay) ** 2
	#print("error: ", error)
	#print("error: ", y - ay)
	#print("sig_prime ", sigmoid_prime(ay))
	#break
	
	ey_d = (y - ay) * sigmoid_prime(ay)
	e2_d = ey_d.dot(wy.T) * relu_prime(a2)
	e1_d = e2_d.dot(w2.T) * sigmoid_prime(a1)
	
	wy += a2.T.dot(ey_d) * L
	by += np.sum(ey_d) * L
	w2 += a1.T.dot(e2_d) * L
	b2 += np.sum(e2_d) * L
	w1 += X.T.dot(e1_d) * L
	b1 += np.sum(e1_d) * L
	
#	wy[wy > 5] = 5
#	by[by > 5] = 5

#	w2[w2 > 5] = 5
#	b2[b2 > 5] = 5

#	w1[w1 > 5] = 5
#	b1[b1 > 5] = 5
	
	
	#print(e1_d)
	epoch += 1

print(y)
print(ay) 
