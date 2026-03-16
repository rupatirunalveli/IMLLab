import requests

files = {
    "deploy.prototxt": "https://raw.githubusercontent.com/arunponnusamy/cvlib-files/master/config/gender_detection/gender_deploy.prototxt",
    "gender_net.caffemodel": "https://huggingface.co/AjaySharma/genderDetection/resolve/main/gender_net.caffemodel"
}

for fname, url in files.items():
    dest = f"face_gender/{fname}"
    print(f"Downloading {fname}...")
    resp = requests.get(url, stream=True)
    resp.raise_for_status()
    with open(dest, "wb") as f:
        for chunk in resp.iter_content(chunk_size=8192):
            f.write(chunk)
    print(f"✅ Saved to {dest}")
