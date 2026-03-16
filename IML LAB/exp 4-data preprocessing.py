import pandas as pd
from sklearn.preprocessing import LabelEncoder, MinMaxScaler

d = pd.read_csv("all_hour.csv")
print("INPUT DATASET:\n", d)
print("\nMISSING VALUES:\n", d.isnull().sum())

d = d.dropna(thresh=len(d.columns)/2)

num = d.select_dtypes(include='number').columns
cat = d.select_dtypes(include='object').columns

d[num] = d[num].fillna(d[num].mean())
d[cat] = d[cat].apply(lambda x: x.fillna(x.mode()[0]))
print("\nDATASET AFTER IMPUTATION:\n", d)

d[cat] = d[cat].apply(LabelEncoder().fit_transform)
print("\nDATASET AFTER ENCODING:\n", d)

Q1, Q3 = d[num].quantile(0.25), d[num].quantile(0.75)
outliers = (d[num] < (Q1-1.5*(Q3-Q1))) | (d[num] > (Q3+1.5*(Q3-Q1)))
print("\nOUTLIERS (True = Outlier):\n", outliers)

d[num] = MinMaxScaler().fit_transform(d[num])
print("\nFINAL OUTPUT DATASET:\n", d)

# Save safely with a new name to avoid permission issues
d.to_csv("all_hour_preprocessed_safe.csv", index=False)
