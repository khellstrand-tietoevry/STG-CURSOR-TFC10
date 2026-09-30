#!/usr/bin/env bash
set -euo pipefail
REPO="$(cd "$(dirname "$0")/../../.." && pwd)"
CANARY_ROOT="$REPO/analysis/TFC10/equivalence/canary/ftfc-l050"
SCRATCH="$(mktemp -d /tmp/tfc10-canary-l050.XXXXXX)"
trap 'rm -rf "$SCRATCH"' EXIT
mkdir -p "$CANARY_ROOT/target/surefire-reports"
python3 - "$REPO/modernized/TFC10/pom.xml" "$SCRATCH/pom.xml" <<'PY'
import sys
from pathlib import Path
src, dst = Path(sys.argv[1]), Path(sys.argv[2])
text = src.read_text()
start = text.index("<modules>")
end = text.index("</modules>") + len("</modules>")
single = """  <modules>
    <module>ftfc-l050</module>
  </modules>"""
Path(dst).write_text(text[:start] + single + text[end:])
PY
cp -R "$REPO/modernized/TFC10/ftfc-l050" "$SCRATCH/"
python3 - "$SCRATCH/ftfc-l050/src/main/java/no/tieto/tfc10/l050/InitialCheckService.java" <<'PY'
import sys
from pathlib import Path
p = Path(sys.argv[1])
text = p.read_text()
old = """        if (backend.error()) {
            return InitialCheckResult.error(backend.statusCode(), backend.message());
        }"""
new = """        if (false && backend.error()) {
            return InitialCheckResult.error(backend.statusCode(), backend.message());
        }"""
if old not in text:
    raise SystemExit("canary break pattern not found")
p.write_text(text.replace(old, new))
PY
set +e
(cd "$SCRATCH" && mvn -q -pl ftfc-l050 test) 2>&1 | tee "$CANARY_ROOT/canary.test-output.txt"
MVN_EC=$?
set -e
cp "$SCRATCH/ftfc-l050/target/surefire-reports/"* "$CANARY_ROOT/target/surefire-reports/"
exit "$MVN_EC"
