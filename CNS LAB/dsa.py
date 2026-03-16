import rsa
import hashlib
import binascii

# 1️⃣ Generate Keys
public_key, private_key = rsa.newkeys(512)

print("----- PUBLIC KEY -----")
print(public_key)

print("\n----- PRIVATE KEY -----")
print(private_key)

# 2️⃣ Enter Message
message = input("\nEnter message: ")
message_bytes = message.encode()

# 3️⃣ Create Hash (SHA-256)
hash_value = hashlib.sha256(message_bytes).hexdigest()
print("\n----- HASH VALUE (SHA-256) -----")
print(hash_value)

# 4️⃣ Create Digital Signature
signature = rsa.sign(message_bytes, private_key, 'SHA-256')
signature_hex = binascii.hexlify(signature).decode()

print("\n----- DIGITAL SIGNATURE -----")
print(signature_hex)

# 5️⃣ Verification
verify_message = input("\nEnter message again for verification: ").encode()

try:
    rsa.verify(verify_message, signature, public_key)
    print("\n----- VERIFICATION RESULT -----")
    print("TRUE CASE ✅ Signature Verified")
except:
    print("\n----- VERIFICATION RESULT -----")
    print("FALSE CASE ❌ Verification Failed")
