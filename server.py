from flask import Flask, request, jsonify
import firebase_admin
from firebase_admin import credentials, firestore, messaging

app = Flask(__name__)


def send_notification(token, title, body):
    message = messaging.Message(
        notification=messaging.Notification(
            title=title,
            body=body
        ),
        token=token
    )
    response = messaging.send(message)
    return response


@app.route("/send_message", methods=["POST"])
def send_message():
    print(request.json)
    data = request.json
    print(data)
    token = data.get("to")
    title = data.get("title")
    text = data.get("body")
    try:
        send_notification(token, title, text)
        return jsonify({"success": True}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500


cred = credentials.Certificate("serviceAccountKey.json")
firebase_admin.initialize_app(cred)
db = firestore.client()

app.run(debug=True, port=80)
