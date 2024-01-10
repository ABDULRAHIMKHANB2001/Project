const express = require('express')
const app = express()
const multer = require('multer');
const JSZip = require('jszip');
const cors = require('cors')
const fs = require('node:fs');
const path = require('path');
app.use(express.static('./Website'))


const upload = multer({ dest: 'uploads/' });
app.use(cors())
app.use(express.json())

app.get('/people', (req, res) => {
  fs.readFile(path.join(__dirname, 'CODE', 'peoples.json'), 'utf8', (err, data) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: 'Internal Server Error' });
    }

    let { peoples: people } = JSON.parse(data);
    return res.json(people)
  })
})


app.post('/people', upload.single('zip'), (req, res) => {
  const zipPath = req.file.path;
  console.log(req.body)
  const { id, name, access } = req.body
  const userId = req.params.userId;
  // Read the existing data from people.json
  fs.readFile(path.join(__dirname, 'CODE', 'peoples.json'), 'utf8', (err, data) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: 'Internal Server Error' });
    }

    let { peoples: people } = JSON.parse(data);
    console.log(people)
    people.push({ id, name, access })
    const temp = {
      peoples: people
    }
    fs.writeFile(path.join(__dirname, 'CODE', 'peoples.json'), JSON.stringify(temp), (err) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: 'Internal Server Error' });
      }
    });
  })
  // Read the zip file
  fs.readFile(zipPath, (err, data) => {
    if (err) {
      console.error('Error reading zip file:', err);
      res.status(500).send('Error reading zip file');
      return;
    }

    // Extract images from the zip
    JSZip.loadAsync(data)
      .then((zip) => {
        const extractPromises = [];
        fs.mkdir(path.join(__dirname, 'CODE', 'images', `cut_${req.body.id}`), { recursive: true }, (err) => {
          if (err) {
            console.error('Error creating directory:', err);
          } else {
            console.log('Directory created successfully!');
          }
        });
        zip.forEach((relativePath, file) => {

          const targetPath = path.join(__dirname, 'CODE', 'images', `cut_${req.body.id}`, relativePath);
          console.log(targetPath)

          if (file.dir) {
            // Create directory if it doesn't exist
            fs.mkdirSync(targetPath, { recursive: true });
          } else {
            // Extract file
            extractPromises.push(file.async('nodebuffer')
              .then((content) => {
                fs.writeFileSync(targetPath, content);
              }));
          }
        });

        // Wait for all files to be extracted
        Promise.all(extractPromises)
          .then(() => {
            // Delete the zip file
            fs.unlink(zipPath, (err) => {
              if (err) {
                console.error('Error deleting zip file:', err);
              }
            });

            res.sendStatus(200);
          })
          .catch((err) => {
            console.error('Error extracting files from zip:', err);
            res.status(500).send('Error extracting files from zip');
          });
      })
      .catch((err) => {
        console.error('Error loading zip file:', err);
        res.status(500).send('Error loading zip file');
      });
  });
});


app.delete('/people/:userId', (req, res) => {
  const userId = req.params.userId;
  let paath = path.join(__dirname, 'CODE', 'images', `${req.params.userId}`)
  deleteFolderRecursive(paath)

  // Read the existing data from people.json
  fs.readFile(path.join(__dirname, 'CODE', 'peoples.json'), 'utf8', (err, data) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: 'Internal Server Error' });
    }

    let { peoples: people } = JSON.parse(data);
    console.log(people)
    // Find the user by id and remove them from the array
    const userIndex = people.findIndex((person) => person.id === userId);
    if (userIndex === -1) {
      return res.status(404).json({ error: 'User not found' });
    }
    people.splice(userIndex, 1);

    // Write the updated data back to people.json
    const temp = {
      peoples: people
    }
    fs.writeFile(path.join(__dirname, 'CODE', 'peoples.json'), JSON.stringify(temp), (err) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: 'Internal Server Error' });
      }
      res.json({ message: 'User deleted successfully' });
    });
  });
});

app.patch('/people/:userId', (req, res) => {
  const userId = req.params.userId;
  // Read the existing data from people.json
  fs.readFile(path.join(__dirname, 'CODE', 'peoples.json'), 'utf8', (err, data) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: 'Internal Server Error' });
    }

    let { peoples: people } = JSON.parse(data);
    // Find the user by id and remove them from the array
    const userIndex = people.findIndex((person) => person.id === userId);
    if (userIndex === -1) {
      return res.status(404).json({ error: 'User not found' });
    }
    // people.splice(userIndex, 1);

    people[userIndex].name = req.body.name
    let isTrueSet = (req.body.access == 'true' || req.body.access == true || req.body.access === true || req.body.access === 'true');
    people[userIndex].access = isTrueSet

    console.log(people[userIndex])
    // Write the updated data back to people.json
    const temp = {
      peoples: people
    }
    console.log('temp', temp)
    fs.writeFile(path.join(__dirname, 'CODE', 'peoples.json'), JSON.stringify(temp), (err) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: 'Internal Server Error' });
      }
      res.json({ message: 'User updated successfully' });
    });
  });
});

app.listen(3000, () => {
  console.log("[+]Lock and loaded")
})


function deleteFolderRecursive(folderPath) {
  if (fs.existsSync(folderPath)) {
      fs.readdirSync(folderPath).forEach((file) => {
          const currentPath = path.join(folderPath, file);

          if (fs.lstatSync(currentPath).isDirectory()) {
              deleteFolderRecursive(currentPath);
          } else {
              fs.unlinkSync(currentPath);
          }
      });

      fs.rmdirSync(folderPath);
      console.log(`Deleted folder: ${folderPath}`);
  } else if (fs.existsSync(folderPath.replace('cut_', ''))) {
      console.log(`Deleted folder: ${folderPath.replace('cut_', '')}`);
  } else {
      console.log(`Folder does not exist: ${folderPath}`);
  }
}
