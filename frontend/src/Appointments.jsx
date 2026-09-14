import { useEffect, useState } from "react";

function Appointments() {
  const [appointments, setAppointments] = useState([]);
  const [status, setStatus] = useState("ALL");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);

    const url =
      status === "ALL"
        ? "http://localhost:8080/api/appointments"
        : `http://localhost:8080/api/appointments/status/${status}`;

    fetch(url)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to load appointments");
        }
        return response.json();
      })
      .then((data) => {
        setAppointments(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Appointment loading error:", error);
        setLoading(false);
      });
  }, [status]);

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>Appointments</h1>
          <p>Manage patient appointments and schedules</p>
        </div>

        <select
          className="search-input"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="ALL">All Appointments</option>
          <option value="SCHEDULED">Scheduled</option>
          <option value="COMPLETED">Completed</option>
        </select>
      </div>

      {loading ? (
        <p>Loading appointments...</p>
      ) : (
        <div className="table-card">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Patient ID</th>
                <th>Doctor ID</th>
                <th>Appointment Time</th>
                <th>Reason</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
              {appointments.map((appointment) => (
                <tr key={appointment.appointmentId}>
                  <td>{appointment.appointmentId}</td>
                  <td>{appointment.patientId}</td>
                  <td>{appointment.doctorId}</td>
                  <td>{appointment.appointmentTime}</td>
                  <td>{appointment.reason}</td>
                  <td>
                    <span className="status-badge">
                      {appointment.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {appointments.length === 0 && (
            <p className="empty-message">No appointments found.</p>
          )}
        </div>
      )}
    </div>
  );
}

export default Appointments;