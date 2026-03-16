import csv
import numpy as np
import os
from sklearn.preprocessing import LabelEncoder
from sklearn.naive_bayes import CategoricalNB

# Load CSV
base_dir = os.path.dirname(__file__)
csv_path = os.path.join(base_dir, "naive_bayes_words.csv")

# Read CSV and convert ALL data to lowercase
d = [[x.strip().lower() for x in row] for row in csv.reader(open(csv_path))]
h, r = d[0], np.array(d[1:])

# Encode features
e = {}
a = np.zeros(r.shape, int)

for i, x in enumerate(h):
    le = LabelEncoder()
    a[:, i] = le.fit_transform(r[:, i])
    e[x] = le

# Train model
m = CategoricalNB().fit(a[:, :-1], a[:, -1])

# Take input (convert to lowercase)
print("\nEnter input values:")
v = [
    input("Outlook (Sunny/Overcast/Rain): ").strip().lower(),
    input("Temperature (Hot/Mild/Cool): ").strip().lower(),
    input("Humidity (High/Normal): ").strip().lower(),
    input("Wind (Weak/Strong): ").strip().lower()
]

# Predict
u = np.array([[e[x].transform([y])[0] for x, y in zip(h[:-1], v)]])
p = m.predict(u)
pr = m.predict_proba(u)

# Output

print("\nEntered input values:")
for x, y in zip(h[:-1], v):
    print(f"{x}: {y}")

print("\nPrediction:", e["playtennis"].inverse_transform(p)[0])
print("Class Probabilities:", pr)
