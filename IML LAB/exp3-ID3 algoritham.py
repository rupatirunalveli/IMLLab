import math
import csv
from collections import Counter

# ---------- Read CSV ----------
def load_csv(filename):
    with open(filename, 'r') as file:
        reader = csv.DictReader(file)
        data = [row for row in reader]
    return data, reader.fieldnames

# ---------- Entropy ----------
def entropy(data):
    counts = Counter(data)
    total = sum(counts.values())
    return -sum((c/total) * math.log2(c/total) for c in counts.values())

# ---------- Information Gain ----------
def info_gain(data, attr, target):
    total_entropy = entropy([row[target] for row in data])

    values = set(row[attr] for row in data)
    weighted_entropy = 0

    for val in values:
        subset = [row for row in data if row[attr] == val]
        weighted_entropy += (len(subset)/len(data)) * \
                            entropy([row[target] for row in subset])

    return total_entropy - weighted_entropy

# ---------- ID3 Algorithm ----------
def id3(data, features, target):
    targets = [row[target] for row in data]

    # If all belong to same class
    if len(set(targets)) == 1:
        return targets[0]

    # If no features left
    if not features:
        return Counter(targets).most_common(1)[0][0]

    # Choose best attribute
    gains = {f: info_gain(data, f, target) for f in features}
    best = max(gains, key=gains.get)

    tree = {best: {}}
    values = set(row[best] for row in data)

    for val in values:
        subset = [row for row in data if row[best] == val]
        tree[best][val] = id3(
            subset,
            [f for f in features if f != best],
            target
        )

    return tree

# ---------- Prediction ----------
def classify(sample, tree):
    attr = next(iter(tree))
    value = sample[attr]
    result = tree[attr][value]
    return classify(sample, result) if isinstance(result, dict) else result

# ---------- MAIN ----------
data, columns = load_csv("play_tennis.csv")

features = columns[:-1]   # All except target
target = columns[-1]

tree = id3(data, features, target)
print("Decision Tree:", tree)

sample = {
    'Outlook': 'Sunny',
    'Temperature': 'Cool',
    'Humidity': 'High',
    'Wind': 'Strong'
}

print("Prediction:", classify(sample, tree))









