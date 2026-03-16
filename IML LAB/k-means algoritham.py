import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans

# ===============================
# 1️⃣ Load Customer Dataset
# ===============================
# Replace with your file name
data = pd.read_csv("customers.csv")

print("Customer Dataset:")
print(data.head())

# ===============================
# 2️⃣ Select Features for Clustering
# Example: Annual Income & Spending Score
# Change column names according to your CSV
# ===============================
X = data[['Annual Income (k$)', 'Spending Score (1-100)']]

# ===============================
# 3️⃣ Apply K-Means Algorithm
# ===============================
k = 5  # Number of clusters

kmeans = KMeans(n_clusters=k, random_state=42)
data['Cluster'] = kmeans.fit_predict(X)

# ===============================
# 4️⃣ Print Cluster Formation
# ===============================
for i in range(k):
    print(f"\nCluster {i}")
    print(data[data['Cluster'] == i])

# ===============================
# 5️⃣ Plot Graph of Clusters
# ===============================
plt.figure()

for i in range(k):
    cluster_points = data[data['Cluster'] == i]
    plt.scatter(
        cluster_points['Annual Income (k$)'],
        cluster_points['Spending Score (1-100)']
    )

# Plot centroids
plt.scatter(
    kmeans.cluster_centers_[:, 0],
    kmeans.cluster_centers_[:, 1],
    marker='X',
    s=200
)

plt.title("Customer Segmentation using K-Means")
plt.xlabel("Annual Income (k$)")
plt.ylabel("Spending Score (1-100)")
plt.show()
