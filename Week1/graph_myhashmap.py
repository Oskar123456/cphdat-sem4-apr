import matplotlib.pyplot as plt
import numpy as np

bdata_x, bdata_y = np.loadtxt("data/benchmarks_myhashmap_time.txt")
hdata_x, hdata_y = np.loadtxt("data/benchmarks_myhashmap_bucketsize.txt")

fig, ax = plt.subplots()

ax.scatter(bdata_x, bdata_y, label="time")
ax.scatter(hdata_x, hdata_y, label="bucket sizes")

ax.legend()
ax.grid(True)

plt.show()

