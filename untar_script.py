import tarfile
import os

# Define the path to the tar file and the destination directory
tar_file_path = 'fo_installer-RHEL8.6_23.0.0_FP_03_635338.tar'
destination_dir = '/home/ec2-user'

# Ensure the destination directory exists
os.makedirs(destination_dir, exist_ok=True)

# Open the tar file and extract its contents
with tarfile.open(tar_file_path, 'r:gz') as tar:
    tar.extractall(path=destination_dir)
    print(f"Extracted all files to {destination_dir}")

# Optional: List all extracted files
for member in tar.getnames():
    print(member)
