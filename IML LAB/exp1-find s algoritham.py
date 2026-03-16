# finds.py
import csv

def find_s_algorithm(examples):
    """
    Apply the Find-S algorithm to a set of training examples.
    Each example is a tuple: (attributes, label)
    """
    hypothesis = ["0"] * len(examples[0][0])

    for attributes, label in examples:
        if label == "Yes":
            for i in range(len(hypothesis)):
                if hypothesis[i] == "0":
                    hypothesis[i] = attributes[i]
                elif hypothesis[i] != attributes[i]:
                    hypothesis[i] = "?"
    return hypothesis


if __name__ == "__main__":
    dataset = []

    # Load dataset from CSV (NO pandas)
    with open("finds.csv", "r") as file:
        reader = csv.reader(file)

        header = next(reader)  # skip column names

        for row in reader:
            attributes = row[:-1]       # all columns except last
            label = row[-1]            # last column is target
            dataset.append((attributes, label))

    # Run Find-S
    final_hypothesis = find_s_algorithm(dataset)
    print("Final Hypothesis:", final_hypothesis)
