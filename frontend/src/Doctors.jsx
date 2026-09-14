import { useEffect, useState } from "react";

function Doctors() {
  const [doctors, setDoctors] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);
  const [selectedDoctor, setSelectedDoctor] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/api/doctors")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to load doctors");
        }
        return response.json();
      })
      .then((data) => {
        setDoctors(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Doctor loading error:", error);
        setLoading(false);
      });
  }, []);

  const filteredDoctors = doctors.filter((doctor) =>
    `${doctor.doctorName} ${doctor.specialization}`
      .toLowerCase()
      .includes(search.toLowerCase())
  );

  if (selectedDoctor) {
    return (
      <div className="page">
        <div className="page-header">
          <div>
            <h1>Doctor Details</h1>
            <p>Doctor profile and professional information</p>
          </div>

          <button
            className="primary-button"
            onClick={() => setSelectedDoctor(null)}
          >
            Back to Doctors
          </button>
        </div>

        <div className="report-header-card">
          <div>
            <span>Doctor</span>
            <strong>{selectedDoctor.doctorName}</strong>
          </div>

          <div>
            <span>Specialization</span>
            <strong>{selectedDoctor.specialization}</strong>
          </div>

          <div>
            <span>Phone</span>
            <strong>{selectedDoctor.phone}</strong>
          </div>

          <div>
            <span>Status</span>
            <strong>{selectedDoctor.status}</strong>
          </div>
        </div>

        <div className="panel">
          <div className="panel-header">
            <h2>Professional Information</h2>
            <p>Doctor details from Oracle Database</p>
          </div>

          <div className="appointment-summary">
            <div>
              <span>Doctor ID</span>
              <strong>{selectedDoctor.doctorId}</strong>
            </div>

            <div>
              <span>Department ID</span>
              <strong>{selectedDoctor.departmentId}</strong>
            </div>

            <div>
              <span>Joining Date</span>
              <strong>{selectedDoctor.joiningDate}</strong>
            </div>
          </div>

          <div style={{ marginTop: "20px" }}>
            <p>
              <strong>Email:</strong> {selectedDoctor.email}
            </p>

            <p>
              <strong>Phone:</strong> {selectedDoctor.phone}
            </p>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>Doctors</h1>
          <p>View doctors and their clinical specializations</p>
        </div>

        <input
          type="text"
          placeholder="Search doctor or specialization..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="search-input"
        />
      </div>

      {loading ? (
        <p>Loading doctors...</p>
      ) : (
        <div className="table-card">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Doctor</th>
                <th>Specialization</th>
                <th>Department</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
              {filteredDoctors.map((doctor) => (
                <tr
                  key={doctor.doctorId}
                  onClick={() => setSelectedDoctor(doctor)}
                  style={{ cursor: "pointer" }}
                >
                  <td>{doctor.doctorId}</td>

                  <td>
                    <strong>{doctor.doctorName}</strong>
                  </td>

                  <td>{doctor.specialization}</td>
                  <td>{doctor.departmentId}</td>
                  <td>{doctor.phone}</td>
                  <td>{doctor.email}</td>

                  <td>
                    <span className="status-badge">
                      {doctor.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {filteredDoctors.length === 0 && (
            <p className="empty-message">
              No doctors found.
            </p>
          )}
        </div>
      )}
    </div>
  );
}

export default Doctors;