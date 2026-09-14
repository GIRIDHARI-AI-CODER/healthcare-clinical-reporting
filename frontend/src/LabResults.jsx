import { useState } from "react";

function LabResults() {
  const [patientId, setPatientId] = useState("");
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const searchResults = () => {
    if (!patientId || Number(patientId) <= 0) {
      setError("Please enter a valid patient ID.");
      setResults([]);
      return;
    }

    setLoading(true);
    setError("");
    setResults([]);

    fetch(`http://localhost:8080/api/patients/${patientId}/lab-results`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Lab results not found.");
        }

        return response.json();
      })
      .then((data) => {
        setResults(data);
        setLoading(false);

        if (data.length === 0) {
          setError("No lab results found for this patient.");
        }
      })
      .catch((err) => {
        console.error("Lab result error:", err);
        setError("Unable to load lab results.");
        setLoading(false);
      });
  };

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>Lab Results</h1>
          <p>Patient laboratory test results and clinical values</p>
        </div>

        <div className="report-search">
          <input
            type="number"
            placeholder="Enter Patient ID"
            value={patientId}
            onChange={(e) => setPatientId(e.target.value)}
            className="search-input"
          />

          <button
            className="primary-button"
            onClick={searchResults}
          >
            View Results
          </button>
        </div>
      </div>

      {loading && <p>Loading lab results...</p>}

      {error && <p className="error-message">{error}</p>}

      {results.length > 0 && (
        <div className="table-card">
          <table>
            <thead>
              <tr>
                <th>Lab Result ID</th>
                <th>Patient ID</th>
                <th>Test Name</th>
                <th>Result</th>
                <th>Unit</th>
                <th>Reference Range</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
              {results.map((result) => (
                <tr key={result.labResultId}>
                  <td>{result.labResultId}</td>
                  <td>{result.patientId}</td>
                  <td>
                    <strong>{result.testName}</strong>
                  </td>
                  <td>{result.resultValue}</td>
                  <td>{result.unit}</td>
                  <td>{result.referenceRange}</td>
                  <td>
                    <span className="status-badge">
                      {result.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default LabResults;
