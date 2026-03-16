import csv

def load_data(filename):
    with open(filename, 'r') as f:
        reader = csv.reader(f)
        data = [[cell.strip() for cell in row] for row in reader]
    return data

def is_consistent(h, x):
    return all(h[i] == '?' or h[i] == x[i] for i in range(len(h)))

def candidate_elimination(data):
    X = [row[:-1] for row in data]
    Y = [row[-1] for row in data]
    n_attrs = len(X[0])

    # Initialize boundaries
    S = X[0].copy() if Y[0] == "Yes" else ['ϕ'] * n_attrs
    G = [['?' for _ in range(n_attrs)]]

    print("Initial S:", S)
    print("Initial G:", G)
    print("-" * 50)

    for idx, (x, y) in enumerate(zip(X, Y), start=1):
        if y == "Yes":
            # Generalize S minimally
            for i in range(n_attrs):
                if S[i] == 'ϕ':
                    S[i] = x[i]
                elif S[i] != x[i]:
                    S[i] = '?'
            # Remove inconsistent hypotheses from G
            G = [g for g in G if is_consistent(g, x)]
        else:  # Negative example
            new_G = []
            for g in G:
                if is_consistent(g, x):
                    for i in range(n_attrs):
                        if g[i] == '?':
                            for val in set(attr[i] for attr in X):
                                if val != x[i]:
                                    h = g.copy()
                                    h[i] = val
                                    if is_consistent(h, S):
                                        new_G.append(h)
                else:
                    new_G.append(g)
            G = new_G

        # Print trace after each example
        print(f"After example {idx} ({x}, {y}):")
        print("S:", S)
        print("G:", G)
        print("-" * 50)

    # Replace 'ϕ' with '?'
    S = [x if x != 'ϕ' else '?' for x in S]
    return S, G

# Example usage
data = load_data("training.csv")
S, G = candidate_elimination(data)

print("Final Specific Boundary (S):", S)
print("Final General Boundary (G):", G)
