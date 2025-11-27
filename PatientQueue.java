package myproject;
public class PatientQueue {

    private QueueNode front, rear;
    private int size;

    public PatientQueue() {
        front = rear = null;
        size = 0;
    }

    public void enqueue(Patient data) {
        QueueNode newNode = new QueueNode(data);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public Patient dequeue() {
        if (front == null) return null;

        Patient removed = front.data;
        front = front.next;

        if (front == null) rear = null;

        size--;
        return removed;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String displayQueue() {
        if (isEmpty()) return "Queue is empty.";

        StringBuilder sb = new StringBuilder();
        QueueNode temp = front;

        while (temp != null) {
            sb.append(temp.data.toString()).append("\n");
            temp = temp.next;
        }

        return sb.toString();
    }
}
