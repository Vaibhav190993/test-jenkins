import yaml
import sys
import requests
import socket


def load_config(file_path):
    try:
        with open(file_path, 'r') as file:
            return yaml.safe_load(file)
    except Exception as e:
        print(f"Error loading configuration file: {e}")
        sys.exit(1)

def check_socket_connection(ip, port):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.settimeout(5)
    try:
        sock.connect((ip, port))
        return True
    except socket.error as e:
        print(f"Socket connection error: {e}")
        return False
    finally:
        sock.close()

def main():
    config_file = 'test.yml'  # Adjust the path if necessary
    data = load_config(config_file)

    server_ip = data.get('server', {}).get('ip', 'unknown')
    ssh_hostname = data.get('server', {}).get('hostname', 'unknown')
    ssh_port = data.get('server', {}).get('port', 22)
    ssh_username = data.get('server', {}).get('username', 'unknown')
    ssh_password = data.get('server', {}).get('password', 'unknown')

    if check_socket_connection(server_ip, ssh_port):
        print("Socket connection to server is successful.")
    else:
        print("Socket connection to server failed.")

if __name__ == "__main__":
    main()
