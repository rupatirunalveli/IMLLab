import numpy as np
from collections import Counter

class KNN:
    def __init__(self, k=3):
        self.k = k

    def fit(self, X_train, y_train):
        self.X_train = X_train
        self.y_train = y_train
        print("Training Data:")
        for x, y in zip(X_train, y_train):
            print(f"Point: {x}, Label: {y}")
        print("-" * 40)

    def predict(self, X_test):
        print("Test Data:")
        for x in X_test:
            print(f"Point: {x}")
        print("-" * 40)

        predictions = []
        for idx, x in enumerate(X_test):
            print(f"\nProcessing Test Point {x}:")
            distances = np.linalg.norm(self.X_train - x, axis=1)

            for i, d in enumerate(distances):
                print(f"Distance to {self.X_train[i]} (Label {self.y_train[i]}): {d:.2f}")

            # FIXED INDENTATION HERE
            k_indices = np.argsort(distances)[:self.k]
            k_nearest_labels = [self.y_train[i] for i in k_indices]

            print(f"Nearest {self.k} neighbors: {k_nearest_labels}")
            most_common = Counter(k_nearest_labels).most_common(1)[0][0]
            print(f"Predicted Label: {most_common}")

            predictions.append(most_common)

        print("\nFinal Predictions:", predictions)
        return predictions


if __name__ == "__main__":
    X_train = np.array([[1,2],[2,3],[3,3],[6,7],[7,8],[8,8]])
    y_train = np.array(['A','A','A','B','B','B'])
    X_test = np.array([[5,5],[2,2]])

    knn = KNN(k=3)
    knn.fit(X_train, y_train)
    predictions = knn.predict(X_test)
