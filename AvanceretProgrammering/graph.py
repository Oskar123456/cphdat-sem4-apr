import matplotlib.pyplot as plt
import numpy as np

data_x, data_y = np.loadtxt("data/const_out.txt")
logdata_x, logdata_y = np.loadtxt("data/log_out.txt")
lindata_x, lindata_y = np.loadtxt("data/lin_out.txt")
squaredata_x, squaredata_y = np.loadtxt("data/square_out.txt")

fig, ax = plt.subplots()

ax.scatter(data_x, data_y, label='const')
ax.scatter(logdata_x, logdata_y, label='log')
ax.scatter(lindata_x, lindata_y, label='lin')
ax.scatter(squaredata_x, squaredata_y, label='square')

ax.legend()
plt.xlabel("sample size");
plt.ylabel("time");
plt.show();

# print(data_x)
# print(data_y)
